package com.example.kanbanbackend.controllers;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;
import com.example.kanbanbackend.services.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardControllerImpl implements BoardController {

    private final BoardService boardService;
    @Override
    public ApiResult<List<BoardDTO>> get() {
        return boardService.get();
    }
    @Override
    public ApiResult<?> delete(UUID id) {
        return boardService.delete(id);
    }
    @Override
    public ApiResult<BoardDTO> get(UUID id) {
        return boardService.get(id);
    }
    @Override
    public ApiResult<BoardDTO> add(BoardAddDTO boardAddDTO) {
        return boardService.add(boardAddDTO);
    }
    @Override
    public ApiResult<?> edit(UUID id, BoardAddDTO boardAddDTO) {
        return boardService.edit(id, boardAddDTO);
    }

}
