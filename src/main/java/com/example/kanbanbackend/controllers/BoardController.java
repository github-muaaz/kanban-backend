package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/board")
public interface BoardController {

    @GetMapping()
    ApiResult<List<BoardDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<BoardDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<BoardDTO> add(@NotNull @ModelAttribute BoardAddDTO boardAddDTO);

    @PutMapping("/{id}")
    ApiResult<BoardDTO> edit(@PathVariable Integer id, @RequestBody BoardAddDTO boardAddDTO);

}
