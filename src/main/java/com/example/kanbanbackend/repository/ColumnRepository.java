package com.example.kanbanbackend.repository;

import com.example.kanbanbackend.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ColumnRepository extends JpaRepository<Columns, UUID> {

//    List<Columns> findAllByIdMatches(List<UUID> ids);
}
