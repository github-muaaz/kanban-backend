package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.status.StatusDTO;
import com.example.kanbanbackend.services.status.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StatusControllerImpl implements StatusController {

    private final StatusService columnService;
    @Override
    public ApiResult<List<StatusDTO>> get(UUID boardId) {
        return columnService.get(boardId);
    }

    @Override
    public ApiResult<List<StatusDTO>> getCommon() {
        return columnService.getCommon();
    }
}
