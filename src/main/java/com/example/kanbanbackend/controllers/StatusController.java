package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.status.StatusDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/column")
public interface StatusController {

    @GetMapping("/{boardId}")
    ApiResult<List<StatusDTO>> get(@PathVariable UUID boardId);

    @GetMapping("/common")
    ApiResult<List<StatusDTO>> getCommon();
}
