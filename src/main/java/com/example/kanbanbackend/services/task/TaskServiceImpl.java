package com.example.kanbanbackend.services.task;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.task.TaskAddDTO;
import com.example.kanbanbackend.payload.task.TaskDTO;
import com.example.kanbanbackend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public ApiResult<List<TaskDTO>> get(){

        return null;
    }

    public ApiResult<?> delete(Integer id){
        return null;
    }

    public ApiResult<TaskDTO> get(Integer id){
        return null;
    }

    public ApiResult<TaskDTO> add(TaskAddDTO taskAddDTO){
        return null;
    }

    public ApiResult<TaskDTO> edit(Integer id, TaskAddDTO taskAddDTO){
        return null;
    }
}
