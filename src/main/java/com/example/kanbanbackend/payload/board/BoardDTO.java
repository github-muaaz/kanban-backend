package com.example.kanbanbackend.payload.board;

import com.example.kanbanbackend.payload.columns.ColumnDTO;
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
public class BoardDTO {

    private UUID id;

    private String name;

    private List<ColumnDTO> columns;
}
