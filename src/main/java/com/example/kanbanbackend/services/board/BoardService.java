package com.example.kanbanbackend.services.board;

import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;

import java.util.List;

public interface BoardService {

    ApiResult<List<BoardDTO>> get();

    ApiResult<?> delete(Integer id);

    ApiResult<BoardDTO> get(Integer id);

    ApiResult<BoardDTO> add(BoardAddDTO boardAddDTO);

    ApiResult<BoardDTO> edit(Integer id, BoardAddDTO boardAddDTO);
}
