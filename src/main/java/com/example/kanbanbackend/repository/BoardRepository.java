package com.example.kanbanbackend.repository;

import com.example.kanbanbackend.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {

    boolean existsByNameIgnoreCaseAndIdIsNot(String name, UUID id);

    boolean existsByNameIgnoreCase(String name);
}
