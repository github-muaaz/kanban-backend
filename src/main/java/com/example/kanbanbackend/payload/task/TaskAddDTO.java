package com.example.kanbanbackend.payload.task;

import com.example.kanbanbackend.payload.subtask.SubtaskDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskAddDTO {

    private UUID id;

    private UUID statusId;

    private UUID boardId;

    private String title;

    private String description;

    private SubtaskDTO subtasks;
}
