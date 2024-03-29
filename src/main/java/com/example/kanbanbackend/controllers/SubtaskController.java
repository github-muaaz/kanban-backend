package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("api/subtask")
public interface SubtaskController {

    @ApiOperation(value = "Set Status for Subtask path")
    @GetMapping("/{id}/{isCompleted}")
    ApiResult<?> setStatus(@PathVariable UUID id, @PathVariable Boolean isCompleted);

}
