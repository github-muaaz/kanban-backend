package com.example.kanbanbackend.payload.task;

import com.example.kanbanbackend.payload.subtask.SubtaskAddDTO;
import com.example.kanbanbackend.payload.subtask.SubtaskDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskAddDTO {

    private UUID id;

    private UUID statusId;

    private UUID boardId;

    private String title;

    private String description;

    private List<SubtaskAddDTO> subtasks;
}
