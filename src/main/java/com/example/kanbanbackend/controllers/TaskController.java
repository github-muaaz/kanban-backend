package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/task")
public interface TaskController {

    @GetMapping()
    ApiResult<List<TaskDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<TaskDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<TaskDTO> add(@NotNull @ModelAttribute TaskAddDTO taskAddDTO);

    @PutMapping("/{id}")
    ApiResult<TaskDTO> edit(@PathVariable Integer id, @RequestBody TaskAddDTO taskAddDTO);

}
