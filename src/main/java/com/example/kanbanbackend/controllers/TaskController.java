package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping("api/task")
public interface TaskController {

    @ApiOperation(value = "Delete Task path")
    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @ApiOperation(value = "Get Task path")
    @GetMapping("/{id}")
    ApiResult<TaskDTO> get(@PathVariable UUID id);

    @ApiOperation(value = "Set Status for Task path")
    @GetMapping("/{taskId}/{statusId}")
    ApiResult<TaskDTO> setStatus(@PathVariable UUID taskId, @PathVariable UUID statusId);

    @ApiOperation(value = "Add Task path")
    @PostMapping()
    ApiResult<?> add(@NotNull @RequestBody TaskAddDTO taskAddDTO);

    @ApiOperation(value = "Edit Task path")
    @PutMapping("/{id}")
    ApiResult<TaskDTO> edit(@PathVariable UUID id, @RequestBody TaskAddDTO taskAddDTO);

}
