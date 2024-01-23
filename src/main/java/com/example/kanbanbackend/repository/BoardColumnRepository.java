package com.example.kanbanbackend.repository;

import com.example.kanbanbackend.entity.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface BoardColumnRepository extends JpaRepository<BoardColumn, UUID> {

    List<BoardColumn> findAllByBoardId(UUID id);

    @Transactional
    void deleteAllByBoardId(UUID id);
}
