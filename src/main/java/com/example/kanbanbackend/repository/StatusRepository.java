package com.example.kanbanbackend.repository;

import com.example.kanbanbackend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusRepository extends JpaRepository<Status, UUID> {
    Status findByName(String col);

    boolean existsByName(String name);

//    List<Columns> findAllByIdMatches(List<UUID> ids);
}
