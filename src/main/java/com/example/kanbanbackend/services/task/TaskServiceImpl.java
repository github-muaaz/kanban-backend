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
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final StatusRepository statusRepository;
    private final BoardRepository boardRepository;


    @Override
    public ApiResult<?> delete(UUID id) {

        taskRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("TASK NOT FOUND", HttpStatus.NOT_FOUND));

        subtaskRepository.deleteAllByTaskId(id);
        taskRepository.deleteById(id);

        return ApiResult.successResponse("SUCCESSFULLY DELETED");
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
    public ApiResult<?> add(TaskAddDTO taskAddDTO) {

        System.out.println(taskAddDTO.toString());

        Board board = boardRepository.findById(taskAddDTO.getBoardId())
                .orElseThrow(() ->
                        RestException
                                .restThrow("BOARD NOT FOUND", HttpStatus.NOT_FOUND));

        Status status;

        if(taskAddDTO.getStatusId() == null)
            status = getInitialStatus(board);
        else
            status = statusRepository.findById(taskAddDTO.getStatusId())
                .orElseThrow(() ->
                        RestException
                                .restThrow("STATUS NOT FOUND", HttpStatus.NOT_FOUND));

        Task task = Task.builder()
                .board(board)
                .status(status)
                .title(taskAddDTO.getTitle())
                .definition(taskAddDTO.getDescription())
                .build();

        task = taskRepository.save(task);

        Task finalTask = task;

        taskAddDTO.getSubtasks().forEach(subtaskAddDTO -> {
            Subtask subtask = Subtask.builder()
                    .task(finalTask)
                    .title(subtaskAddDTO.getTitle())
                    .isCompleted(!Objects.isNull(subtaskAddDTO.getIsCompleted()) && subtaskAddDTO.getIsCompleted())
                    .build();

            subtaskRepository.save(subtask);
        });

        return ApiResult.successResponse(mapToTaskDTO(task), "TASK SUCCESSFULLY ADDED");
    }

    @Override
    public ApiResult<TaskDTO> edit(UUID id, TaskAddDTO taskAddDTO) {
        Board board = boardRepository.findById(taskAddDTO.getBoardId())
                .orElseThrow(() ->
                        RestException
                                .restThrow("BOARD NOT FOUND", HttpStatus.NOT_FOUND));

        Status status = statusRepository.findById(taskAddDTO.getStatusId())
                .orElseThrow(() ->
                        RestException
                                .restThrow("STATUS NOT FOUND", HttpStatus.NOT_FOUND));

        Task task = Task.builder()
                .id(id)
                .board(board)
                .status(status)
                .title(taskAddDTO.getTitle())
                .definition(taskAddDTO.getDescription())
                .build();

        task = taskRepository.save(task);

        Task finalTask = task;

        subtaskRepository.deleteAllByTaskId(task.getId());

        taskAddDTO.getSubtasks().forEach(subtaskAddDTO -> {
            Subtask subtask = Subtask.builder()
                    .task(finalTask)
                    .title(subtaskAddDTO.getTitle())
                    .isCompleted(!Objects.isNull(subtaskAddDTO.getIsCompleted()) && subtaskAddDTO.getIsCompleted())
                    .build();

            subtaskRepository.save(subtask);
        });

        return ApiResult.successResponse(mapToTaskDTO(task), "TASK SUCCESSFULLY EDITED");
    }

    @Override
    public ApiResult<TaskDTO> setStatus(UUID taskId, UUID statusId) {
        Status status = statusRepository.findById(statusId)
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

    private Status getInitialStatus(Board board) {
        return boardColumnRepository
                .findAllByBoardId(board.getId())
                .stream().min((a, b) -> (int) (a.getColumn().getOrderNum() - b.getColumn().getOrderNum()))
                .orElse(new BoardColumn()).getColumn();
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
