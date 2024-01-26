package com.example.kanbanbackend.services.board;

import com.example.kanbanbackend.entity.*;
import com.example.kanbanbackend.exceptions.RestException;
import com.example.kanbanbackend.payload.api.ApiResult;
import com.example.kanbanbackend.payload.board.BoardAddDTO;
import com.example.kanbanbackend.payload.board.BoardDTO;
import com.example.kanbanbackend.payload.status.StatusAddDTO;
import com.example.kanbanbackend.payload.status.StatusDTO;
import com.example.kanbanbackend.repository.*;
import com.example.kanbanbackend.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final StatusRepository statusRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;

    @Override
    public ApiResult<List<BoardDTO>> get() {

        List<Board> boards = boardRepository.findAll();

        return ApiResult.successResponse(mapToBoardDTO(boards));
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        boardRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("BOARD NOT FOUND", HttpStatus.NOT_FOUND));

        List<Task> tasks = taskRepository.findAllByBoardId(id);

        tasks.forEach(task -> {
            subtaskRepository.deleteAllByTaskId(task.getId());
        });

        taskRepository.deleteAllByBoardId(id);
        boardColumnRepository.deleteAllByBoardId(id);
        boardRepository.deleteById(id);

        return ApiResult.successResponse("SUCCESSFULLY DELETED");
    }

    @Override
    public ApiResult<BoardDTO> get(UUID id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() ->
                        RestException.restThrow("BOARD NOT FOUND", HttpStatus.NOT_FOUND));

        return ApiResult.successResponse(mapToBoardDTO(board));
    }

    @Override
    public ApiResult<BoardDTO> add(BoardAddDTO boardAddDTO) {

        if (boardRepository.existsByNameIgnoreCase(boardAddDTO.getName()))
            throw RestException
                    .restThrow("BOARD ALREADY EXIST", HttpStatus.CONFLICT);

        Board board = Board.builder()
                .name(boardAddDTO.getName())
                .build();

        board = boardRepository.save(board);


        List<Status> statuses = saveColumns(boardAddDTO);

        Board finalBoard = board;
        statuses.forEach(status -> {
            BoardColumn boardColumn = BoardColumn.builder()
                    .board(finalBoard)
                    .column(status)
                    .build();

            boardColumnRepository.save(boardColumn);
        });

        return ApiResult.successResponse(mapToBoardDTO(board), "SUCCESSFULLY ADDED");
    }

    @Override
    public ApiResult<?> edit(UUID id, BoardAddDTO boardAddDTO) {

        Board board = boardRepository.findById(id).orElseThrow(() ->
                RestException
                        .restThrow("BOARD NOT FOUND", HttpStatus.NOT_FOUND));

        if (boardRepository.existsByNameIgnoreCaseAndIdIsNot(boardAddDTO.getName(), board.getId()))
            throw RestException
                    .restThrow("BOARD ALREADY EXIST", HttpStatus.CONFLICT);

        board.setName(boardAddDTO.getName());

        List<Status> statuses = saveColumns(boardAddDTO);

        boardColumnRepository.deleteAllByBoardId(board.getId());

        statuses.forEach(status -> {
            BoardColumn boardColumn = BoardColumn.builder()
                    .board(board)
                    .column(status)
                    .build();

            boardColumnRepository.save(boardColumn);
        });

        return ApiResult.successResponse("SUCCESSFULLY EDITED");
    }

    private List<Status> saveColumns(BoardAddDTO boardAddDTO) {
        List<String> columns = boardAddDTO.getColumns().stream().distinct().toList();

        List<Status> statuses = new ArrayList<>();

        double colOrder = 100.;

        for (String col : columns) {
            Status column = statusRepository.findByName(col);

            if (Objects.isNull(column)) {
                Status status = Status.builder()
                        .name(col)
                        .color(Constants.colors[(int) (Math.random() * (Constants.colors.length - 1))])
                        .orderNum(colOrder)
                        .build();

                colOrder *= 2;

                status = statusRepository.save(status);

                statuses.add(status);
            } else
                statuses.add(column);
        }

        return statuses;
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
