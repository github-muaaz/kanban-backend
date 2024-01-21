package com.example.kanbanbackend.services.subtask;

import com.example.kanbanbackend.entity.Subtask;
import com.example.kanbanbackend.exceptions.RestException;
import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.repository.SubtaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImpl implements SubtaskService {

    private final SubtaskRepository subtaskRepository;

    public ApiResult<?> setStatus(UUID id, Boolean isCompleted){
        Subtask subtask = subtaskRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("SUBTASK NOT FOUND", HttpStatus.NOT_FOUND));

        subtask.setIsCompleted(isCompleted);

        subtaskRepository.save(subtask);

        return ApiResult.successResponse();
    }
}
