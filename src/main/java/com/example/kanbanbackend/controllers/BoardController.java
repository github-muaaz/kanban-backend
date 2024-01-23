package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/board")
public interface BoardController {

    @GetMapping()
    ApiResult<List<BoardDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    ApiResult<BoardDTO> get(@PathVariable UUID id);

    @PostMapping()
    ApiResult<BoardDTO> add(@NotNull @RequestBody BoardAddDTO boardAddDTO);

    @PutMapping("/{id}")
    ApiResult<?> edit(@PathVariable UUID id, @RequestBody BoardAddDTO boardAddDTO);

}
