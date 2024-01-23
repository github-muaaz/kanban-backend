package com.example.kanbanbackend.services.status;

import com.example.kanbanbackend.entity.*;
import com.example.kanbanbackend.exceptions.RestException;
import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.status.StatusDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;
import com.example.kanbanbackend.repository.*;
import com.example.kanbanbackend.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final CommonStatusRepository commonStatusRepository;

    @Override
    public ApiResult<List<StatusDTO>> get(UUID boardId){
        boardRepository.findById(boardId)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO BOARD FOUND", HttpStatus.NOT_FOUND));

        List<BoardColumn> boardColumns = boardColumnRepository.findAllByBoardId(boardId);

        return ApiResult.successResponse(mapToColumnDTO(boardColumns, boardId));
    }

    @Override
    public ApiResult<List<StatusDTO>> getCommon() {
        List<CommonStatus> commonStatuses = commonStatusRepository.findAll();

        return ApiResult.successResponse(mapToColumnDTO(commonStatuses));
    }

    private List<StatusDTO> mapToColumnDTO(List<CommonStatus> commonStatuses) {
//        System.out.println(Constants.commonColumns);

        return commonStatuses
                .stream()
                .map(this::mapToColumnDTO)
                .toList();
    }

    private StatusDTO mapToColumnDTO(CommonStatus status) {
        return StatusDTO.builder()
                .id(status.getId())
                .name(status.getName())
                .color(status.getColor())
                .orderNum(status.getOrderNum())
                .build();
    }

    private List<StatusDTO> mapToColumnDTO(List<BoardColumn> boardColumns, UUID boardId) {
        return boardColumns.stream()
                .map(boardColumn -> mapToColumnDTO(boardColumn.getColumn(), boardId))
                .toList();
    }

    private StatusDTO mapToColumnDTO(Status column, UUID boardId) {
        List<Task> tasks = taskRepository.findAllByBoardIdAndStatusId(boardId, column.getId());

        return StatusDTO.builder()
                .id(column.getId())
                .name(column.getName())
                .color(column.getColor())
                .orderNum(column.getOrderNum())
                .tasks(mapToTaskDTO(tasks))
                .build();
    }

    private List<TaskDTO> mapToTaskDTO(List<Task> tasks) {
        return tasks.stream()
                .map(this::mapToTaskDTO)
                .toList();
    }

    private TaskDTO mapToTaskDTO(Task task) {
        List<Subtask> subtasks = subtaskRepository.findAllByTaskId(task.getId());

        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDefinition())
                .subtasksLength(subtasks.size())
                .completedSubtasks(getCompletedCount(subtasks))
                .build();
    }

    private Integer getCompletedCount(List<Subtask> subtasks) {
        Integer count = 0;

        for (Subtask subtask : subtasks) {
            if (subtask.getIsCompleted())
                count++;
        }
        return count;
    }
}
