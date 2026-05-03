package com.kunal.projectmanager.repository;

import com.kunal.projectmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    long countByStatus(String status);

    long countByDeadlineBefore(LocalDate date);
}