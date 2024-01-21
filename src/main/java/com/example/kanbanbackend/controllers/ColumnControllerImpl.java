package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.columns.ColumnDTO;
import com.example.kanbanbackend.services.column.ColumnsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ColumnControllerImpl implements ColumnController {

    private final ColumnsService columnService;

    public ApiResult<List<ColumnDTO>> get() {
        return columnService.get();
    }

    public ApiResult<?> delete(Integer id) {
        return columnService.delete(id);
    }

    public ApiResult<ColumnDTO> get(Integer id) {
        return columnService.get(id);
    }

    public ApiResult<ColumnDTO> add(ColumnDTO columnAddDTO) {
        return columnService.add(columnAddDTO);
    }

    public ApiResult<ColumnDTO> edit(Integer id, ColumnDTO columnAddDTO) {
        return columnService.edit(id, columnAddDTO);
    }

}
