package com.example.kanbanbackend.services.task;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;

import java.util.List;

public interface TaskService {

    ApiResult<List<TaskDTO>> get();

    ApiResult<?> delete(Integer id);

    ApiResult<TaskDTO> get(Integer id);

    ApiResult<TaskDTO> add(TaskAddDTO taskAddDTO);

    ApiResult<TaskDTO> edit(Integer id, TaskAddDTO taskAddDTO);
}
