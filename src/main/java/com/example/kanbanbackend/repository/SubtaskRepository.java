package com.example.kanbanbackend.repository;

import com.example.kanbanbackend.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, UUID> {

    List<Subtask> findAllByTaskId(UUID id);

    @Transactional
    void deleteAllByTaskId(UUID id);
}
