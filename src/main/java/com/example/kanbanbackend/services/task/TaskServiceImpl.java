package com.example.kanbanbackend.services.task;

import com.example.kanbanbackend.entity.*;
import com.example.kanbanbackend.exceptions.RestException;
import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.status.StatusDTO;
import com.example.kanbanbackend.payload.subtask.SubtaskDTO;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;
import com.example.kanbanbackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final StatusRepository columnRepository;


    @Override
    public ApiResult<?> delete(UUID id) {
        return null;
    }

    @Override
    public ApiResult<TaskDTO> get(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(
                        () -> RestException
                                .restThrow("TASK NOT FOUND", HttpStatus.NOT_FOUND));

        return ApiResult.successResponse(mapToTaskDTO(task));
    }

    @Override
    public ApiResult<TaskDTO> add(TaskAddDTO taskAddDTO) {
        return null;
    }

    @Override
    public ApiResult<TaskDTO> edit(UUID id, TaskAddDTO taskAddDTO) {
        return null;
    }

    @Override
    public ApiResult<TaskDTO> setStatus(UUID taskId, UUID statusId) {
        Status status = columnRepository.findById(statusId)
                .orElseThrow(
                        () -> RestException
                                .restThrow("STATUS NOT FOUND", HttpStatus.NOT_FOUND));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> RestException
                                .restThrow("TASK NOT FOUND", HttpStatus.NOT_FOUND));

        task.setStatus(status);
        
        task = taskRepository.save(task);

        return ApiResult.successResponse(mapToTaskDTO(task));
    }

    private TaskDTO mapToTaskDTO(Task task) {
        List<Subtask> subtasks = subtaskRepository.findAllByTaskId(task.getId());
        List<BoardColumn> boardColumns = boardColumnRepository.findAllByBoardId(task.getBoard().getId());

        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .statusId(task.getStatus().getId())
                .statuses(mapToColumnDTO(boardColumns))
                .subtasks(mapToSubtaskDTO(subtasks))
                .description(task.getDefinition())
                .subtasksLength(subtasks.size())
                .completedSubtasks(getCompletedCount(subtasks))
                .build();
    }

    private List<StatusDTO> mapToColumnDTO(List<BoardColumn> boardColumns) {
        return boardColumns.stream().map(boardColumn -> mapToColumnDTO(boardColumn.getColumn())).toList();
    }

    private StatusDTO mapToColumnDTO(Status column) {
        return StatusDTO.builder()
                .id(column.getId())
                .name(column.getName())
                .color(column.getColor())
                .orderNum(column.getOrderNum())
                .build();
    }

    private List<SubtaskDTO> mapToSubtaskDTO(List<Subtask> subtasks) {
        return subtasks.stream()
                .map(this::mapToSubtaskDTO)
                .toList();
    }

    private SubtaskDTO mapToSubtaskDTO(Subtask subtask) {
        return SubtaskDTO.builder()
                .id(subtask.getId())
                .title(subtask.getTitle())
                .isCompleted(subtask.getIsCompleted())
                .build();
    }

    private Integer getCompletedCount(List<Subtask> subtasks) {
        return subtasks.stream().filter(Subtask::getIsCompleted).toList().size();
    }
}
