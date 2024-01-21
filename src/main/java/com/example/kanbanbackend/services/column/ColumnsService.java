package com.example.kanbanbackend.services.column;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.columns.ColumnDTO;

import java.util.List;

public interface ColumnsService {

    ApiResult<List<ColumnDTO>> get();

    ApiResult<?> delete(Integer id);

    ApiResult<ColumnDTO> get(Integer id);

    ApiResult<ColumnDTO> add(ColumnDTO columnsDTO);

    ApiResult<ColumnDTO> edit(Integer id, ColumnDTO columnsDTO);
}
