package com.example.kanbanbackend.repository;

import com.example.kanbanbackend.entity.CommonStatus;
import com.example.kanbanbackend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommonStatusRepository extends JpaRepository<CommonStatus, UUID> {

//    List<Columns> findAllByIdMatches(List<UUID> ids);
}
