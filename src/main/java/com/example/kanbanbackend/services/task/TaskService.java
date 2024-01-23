package com.example.kanbanbackend.services.task;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    ApiResult<?> delete(UUID id);

    ApiResult<TaskDTO> get(UUID id);

    ApiResult<?> add(TaskAddDTO taskAddDTO);

    ApiResult<TaskDTO> edit(UUID id, TaskAddDTO taskAddDTO);

    ApiResult<TaskDTO> setStatus(UUID taskId, UUID statusId);
}
