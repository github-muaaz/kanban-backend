package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.columns.ColumnDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/column")
public interface ColumnController {

    @GetMapping()
    ApiResult<List<ColumnDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<ColumnDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<ColumnDTO> add(@NotNull @ModelAttribute ColumnDTO columnDTO);

    @PutMapping("/{id}")
    ApiResult<ColumnDTO> edit(@PathVariable Integer id, @RequestBody ColumnDTO columnDTO);

}
