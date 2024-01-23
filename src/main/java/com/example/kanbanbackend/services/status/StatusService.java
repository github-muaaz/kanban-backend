package com.example.kanbanbackend.services.status;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.status.StatusDTO;

import java.util.List;
import java.util.UUID;

public interface StatusService {

    ApiResult<List<StatusDTO>> get(UUID boardId);

    ApiResult<List<StatusDTO>> getCommon();
}
