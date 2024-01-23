package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping("api/task")
public interface TaskController {

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    ApiResult<TaskDTO> get(@PathVariable UUID id);

    @GetMapping("/{taskId}/{statusId}")
    ApiResult<TaskDTO> setStatus(@PathVariable UUID taskId, @PathVariable UUID statusId);

    @PostMapping()
    ApiResult<?> add(@NotNull @RequestBody TaskAddDTO taskAddDTO);

    @PutMapping("/{id}")
    ApiResult<TaskDTO> edit(@PathVariable UUID id, @RequestBody TaskAddDTO taskAddDTO);

}
