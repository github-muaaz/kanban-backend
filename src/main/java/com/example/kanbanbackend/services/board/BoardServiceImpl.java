package com.example.kanbanbackend.services.board;

import com.example.kanbanbackend.entity.Board;
import com.example.kanbanbackend.entity.BoardColumn;
import com.example.kanbanbackend.entity.Status;
import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;
import com.example.kanbanbackend.payload.status.StatusDTO;
import com.example.kanbanbackend.repository.BoardColumnRepository;
import com.example.kanbanbackend.repository.BoardRepository;
import com.example.kanbanbackend.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final StatusRepository columnRepository;
    private final BoardColumnRepository boardColumnRepository;

    @Override
    public ApiResult<List<BoardDTO>> get(){

        List<Board> boards = boardRepository.findAll();

        return ApiResult.successResponse(mapToBoardDTO(boards));
    }
    @Override
    public ApiResult<?> delete(Integer id){
        return null;
    }
    @Override
    public ApiResult<BoardDTO> get(Integer id){

        return null;
    }
    @Override
    public ApiResult<BoardDTO> add(BoardAddDTO boardAddDTO){



        return null;
    }
    @Override
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

    private List<StatusDTO> mapToColumnDTO(List<BoardColumn> boardColumns) {
        return boardColumns.stream()
                .map(boardColumn -> mapToColumnDTO(boardColumn.getColumn()))
                .toList();
    }

    private StatusDTO mapToColumnDTO(Status column) {
        return StatusDTO.builder()
                .id(column.getId())
                .name(column.getName())
                .color(column.getColor())
                .orderNum(column.getOrderNum())
                .build();
    }

}
