package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;
import com.example.kanbanbackend.services.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController{

    private final TaskService taskService;

    public ApiResult<List<TaskDTO>> get(){
        return taskService.get();
    }

    public ApiResult<?> delete(Integer id){
        return taskService.delete(id);
    }

    public ApiResult<TaskDTO> get(Integer id){
        return taskService.get(id);
    }

    public ApiResult<TaskDTO> add(TaskAddDTO taskAddDTO){
        return taskService.add(taskAddDTO);
    }

    public ApiResult<TaskDTO> edit(Integer id, TaskAddDTO taskAddDTO){
        return taskService.edit(id, taskAddDTO);
    }

}
