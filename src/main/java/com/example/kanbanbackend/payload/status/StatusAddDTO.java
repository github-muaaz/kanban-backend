package com.example.kanbanbackend.payload.status;

import com.example.kanbanbackend.payload.task.TaskDTO;
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
public class StatusAddDTO {

    private UUID id;

    private String name;

    private String color;

    private Double orderNum;
}
