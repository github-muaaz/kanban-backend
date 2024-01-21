package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.services.subtask.SubtaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SubtaskControllerImpl implements SubtaskController {

    private final SubtaskService subtaskService;

    @Override
    public ApiResult<?> setStatus(UUID id, Boolean isCompleted) {
        return subtaskService.setStatus(id, isCompleted);
    }
}
