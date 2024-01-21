package com.example.kanbanbackend.services.column;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.columns.ColumnDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnsServiceImpl implements ColumnsService{

    public ApiResult<List<ColumnDTO>> get(){
        return null;
    }

    public ApiResult<?> delete(Integer id){
        return null;
    }

    public ApiResult<ColumnDTO> get(Integer id){
        return null;
    }

    public ApiResult<ColumnDTO> add(ColumnDTO columnsDTO){
        return null;
    }

    public ApiResult<ColumnDTO> edit(Integer id, ColumnDTO columnsDTO){
        return null;
    }
}
