package com.example.kanbanbackend.payload.columns;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColumnDTO {

    private UUID id;

    private String name;

    private String color;

    private Double orderNum;
}
