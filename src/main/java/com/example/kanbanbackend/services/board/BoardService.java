package com.example.kanbanbackend.services.board;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;

import java.util.List;
import java.util.UUID;

public interface BoardService {

    ApiResult<List<BoardDTO>> get();

    ApiResult<?> delete(UUID id);

    ApiResult<BoardDTO> get(UUID id);

    ApiResult<BoardDTO> add(BoardAddDTO boardAddDTO);

    ApiResult<?> edit(UUID id, BoardAddDTO boardAddDTO);
}
