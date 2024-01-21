package com.example.kanbanbackend.services.subtask;

import com.example.kanbanbackend.payload.api.ApiResult;

import java.util.List;
import java.util.UUID;

public interface SubtaskService {

    ApiResult<?> setStatus(UUID id, Boolean isCompleted);
}
