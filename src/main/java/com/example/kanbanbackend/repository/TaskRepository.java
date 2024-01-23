package com.example.kanbanbackend.repository;

import com.example.kanbanbackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByBoardIdAndStatusId(UUID boardId, UUID statusId);

    @Transactional
    void deleteAllByBoardId(UUID id);

    List<Task> findAllByBoardId(UUID id);
}
