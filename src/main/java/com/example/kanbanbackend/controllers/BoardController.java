package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/board")
public interface BoardController {

    @ApiOperation(value = "Get Boards path [returns list]")
    @GetMapping()
    ApiResult<List<BoardDTO>> get();

    @ApiOperation(value = "Delete Board path")
    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @ApiOperation(value = "Get Board path")
    @GetMapping("/{id}")
    ApiResult<BoardDTO> get(@PathVariable UUID id);

    @ApiOperation(value = "Add Board path")
    @PostMapping()
    ApiResult<BoardDTO> add(@NotNull @RequestBody BoardAddDTO boardAddDTO);

    @ApiOperation(value = "Edit Board path")
    @PutMapping("/{id}")
    ApiResult<?> edit(@PathVariable UUID id, @RequestBody BoardAddDTO boardAddDTO);

}
