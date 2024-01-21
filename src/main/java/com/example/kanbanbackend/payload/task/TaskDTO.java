package com.example.kanbanbackend.payload.task;

import com.example.kanbanbackend.payload.status.StatusDTO;
import com.example.kanbanbackend.payload.subtask.SubtaskDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

    private UUID id;

    private String title;

    private String description;

    private UUID statusId;

    private List<StatusDTO> statuses;

    private List<SubtaskDTO> subtasks;

    private Integer subtasksLength;

    private Integer completedSubtasks;
}
