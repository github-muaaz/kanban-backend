package com.example.kanbanbackend.repository;

import com.example.kanbanbackend.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, UUID> {

}
