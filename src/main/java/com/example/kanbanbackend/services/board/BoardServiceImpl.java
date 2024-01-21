package com.example.kanbanbackend.services.board;

import com.example.kanbanbackend.entity.Board;
import com.example.kanbanbackend.entity.BoardColumn;
import com.example.kanbanbackend.entity.Columns;
import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;
import com.example.kanbanbackend.payload.columns.ColumnDTO;
import com.example.kanbanbackend.repository.BoardColumnRepository;
import com.example.kanbanbackend.repository.BoardRepository;
import com.example.kanbanbackend.repository.ColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ColumnRepository columnRepository;
    private final BoardColumnRepository boardColumnRepository;

    public ApiResult<List<BoardDTO>> get(){

        List<Board> boards = boardRepository.findAll();

        return ApiResult.successResponse(mapToBoardDTO(boards));
    }

    public ApiResult<?> delete(Integer id){
        return null;
    }

    public ApiResult<BoardDTO> get(Integer id){
        return null;
    }

    public ApiResult<BoardDTO> add(BoardAddDTO boardAddDTO){



        return null;
    }

    public ApiResult<BoardDTO> edit(Integer id, BoardAddDTO boardAddDTO){
        return null;
    }

    private List<BoardDTO> mapToBoardDTO(List<Board> boards) {
        return boards.stream()
                .map(this::mapToBoardDTO)
                .toList();
    }

    private BoardDTO mapToBoardDTO(Board board) {
        List<BoardColumn> boardColumns = boardColumnRepository.findAllByBoardId(board.getId());

        return BoardDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .columns(mapToColumnDTO(boardColumns))
                .build();
    }

    private List<ColumnDTO> mapToColumnDTO(List<BoardColumn> boardColumns) {
        return boardColumns.stream()
                .map(boardColumn -> mapToColumnDTO(boardColumn.getColumn()))
                .toList();
    }

    private ColumnDTO mapToColumnDTO(Columns column) {
        return ColumnDTO.builder()
                .id(column.getId())
                .name(column.getName())
                .color(column.getColor())
                .orderNum(column.getOrderNum())
                .build();
    }

}
