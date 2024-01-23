package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;
import com.example.kanbanbackend.services.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController{

    private final TaskService taskService;
    @Override
    public ApiResult<?> delete(UUID id){
        return taskService.delete(id);
    }
    @Override
    public ApiResult<TaskDTO> get(UUID id){
        return taskService.get(id);
    }

    @Override
    public ApiResult<TaskDTO> setStatus(UUID taskId, UUID statusId) {
        return taskService.setStatus(taskId, statusId);
    }

    @Override
    public ApiResult<?> add(TaskAddDTO taskAddDTO){
        return taskService.add(taskAddDTO);
    }
    @Override
    public ApiResult<TaskDTO> edit(UUID id, TaskAddDTO taskAddDTO){
        return taskService.edit(id, taskAddDTO);
    }

}
