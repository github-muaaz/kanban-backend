package com.example.kanbanbackend.payload.subtask;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubtaskDTO {

    private UUID id;

    private String title;

    private Boolean isCompleted;
}
