package com.example.kanbanbackend.component;

import com.example.kanbanbackend.entity.*;
import com.example.kanbanbackend.repository.*;
import com.example.kanbanbackend.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private final BoardRepository boardRepository;
    private final ColumnRepository columnRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;

    @Override
    public void run(String... args) {

        if (Objects.equals(ddlMode, "create")) {
            System.out.println("\n\n\n\n\n\n\n\nyou are running with CREATE ddl mode");
            System.out.println("enter 12 to continue");
            Scanner scanner = new Scanner(System.in);

            int input = scanner.nextInt();

            if (!Objects.equals(input, 12))
                System.exit(1);
        }


        if (Objects.equals(ddlMode, "create")) {

            saveDefaults();
        }
    }


    private void saveDefaults() {
        List<Columns> columns = Constants.commonColumns;

        columns.add(Columns.builder()
                .color(Constants.colors[0])
                .orderNum(100.)
                .name("todo")
                .build());

        columns.add(Columns.builder()
                .color(Constants.colors[1])
                .orderNum(200.)
                .name("doing")
                .build());

        columns.add(Columns.builder()
                .color(Constants.colors[2])
                .orderNum(300.)
                .name("done")
                .build());

        columns = columnRepository.saveAll(columns);

        List<Board> boards = new ArrayList<>();

        boards.add(Board.builder()
                .name("Platform Launch")
                .build());

        boards.add(Board.builder()
                .name("Marketing Plan")
                .build());

        boards.add(Board.builder()
                .name("Roadmap")
                .build());

        boards = boardRepository.saveAll(boards);

        List<BoardColumn> boardColumns = new ArrayList<>();

        for (Board board : boards) {
            for (Columns column : columns) {
                boardColumns.add(BoardColumn.builder()
                        .board(board)
                        .column(column)
                        .build());
            }
        }

        boardColumnRepository.saveAll(boardColumns);


        List<Task> tasks = new ArrayList<>();

        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(0))
                .status(columns.get(0))
                .title("Build UI for onboarding flow")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(0))
                .status(columns.get(0))
                .title("Talk to potential customers about our proposed solution and ask for fair price expectancy")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(0))
                .status(columns.get(0))
                .title("Build UI")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(0))
                .status(columns.get(1))
                .title("Outline a business model that works for our solution")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(0))
                .status(columns.get(1))
                .title("Build UI for search")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(0))
                .status(columns.get(2))
                .title("Research competitor pricing and business models")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(1))
                .status(columns.get(0))
                .title("Research pricing points of various competitors and trial different business models")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(1))
                .status(columns.get(1))
                .title("Add account management endpoints")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(1))
                .status(columns.get(1))
                .title("Design settings and search pages")
                .build());
        tasks.add(Task.builder()
                .definition("We know what we're planning to build for version one. Now we need to finalise the first pricing model we'll use. Keep iterating the subtasks until we have a coherent proposition.")
                .board(boards.get(2))
                .status(columns.get(0))
                .title("Review results of usability tests and iterate")
                .build());

        tasks = taskRepository.saveAll(tasks);


        List<Subtask> subtasks = new ArrayList<>();

        subtasks.add(Subtask.builder()
                .title("Research competitor pricing and business models")
                .task(tasks.get(1))
                .build());

        subtasks.add(Subtask.builder()
                .title("Outline a business model that works for our solution")
                .task(tasks.get(1))
                        .isCompleted(true)
                .build());

        subtasks.add(Subtask.builder()
                .title("Talk to potential customers about our proposed solution and ask for fair price expectancy")
                .task(tasks.get(1))
                        .isCompleted(true)
                .build());
        subtasks.add(Subtask.builder()
                .title("Research competitor pricing and business models")
                .task(tasks.get(2))
                .build());
        subtasks.add(Subtask.builder()
                .title("Talk to potential customers about our proposed solution and ask for fair price expectancy")
                .task(tasks.get(2))
                .build());
        subtasks.add(Subtask.builder()
                .title("Research competitor pricing and business models")
                .task(tasks.get(3))
                .build());
        subtasks.add(Subtask.builder()
                .title("Outline a business model that works for our solution")
                .task(tasks.get(3))
                .build());
        subtasks.add(Subtask.builder()
                .title("Talk to potential customers about our proposed solution and ask for fair price expectancy'")
                .task(tasks.get(4))
                .build());
        subtasks.add(Subtask.builder()
                .title("Research competitor pricing and business models")
                .task(tasks.get(5))
                .build());
        subtasks.add(Subtask.builder()
                .title("Research competitor pricing and business models")
                .task(tasks.get(5))
                .build());
        subtasks.add(Subtask.builder()
                .title("Talk to potential customers about our proposed solution and ask for fair price expectancy")
                .task(tasks.get(5))
                .build());
        subtasks.add(Subtask.builder()
                .title("Research competitor pricing and business models")
                .task(tasks.get(6))
                .build());
        subtasks.add(Subtask.builder()
                .title("Outline a business model that works for our solution")
                .task(tasks.get(6))
                .build());
        subtasks.add(Subtask.builder()
                .title("Talk to potential customers about our proposed solution and ask for fair price expectancy")
                .task(tasks.get(7))
                .build());

        subtasks = subtaskRepository.saveAll(subtasks);
    }

}
