package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.status.StatusDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/column")
public interface StatusController {

    @ApiOperation(value = "Get Columns of Board path")
    @GetMapping("/{boardId}")
    ApiResult<List<StatusDTO>> get(@PathVariable UUID boardId);

    @ApiOperation(value = "Get Common Columns path [returns list: default values(todo, doing, done) as object]")
    @GetMapping("/common")
    ApiResult<List<StatusDTO>> getCommon();
}
