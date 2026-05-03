package com.kunal.projectmanager.repository;

import com.kunal.projectmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // ✅ Current (keep for now - backward compatibility)
    List<Task> findByProjectId(Long projectId);

    // ✅ Future (when Project entity added)
    List<Task> findByProject_Id(Long projectId);

    // ✅ Count APIs
    long countByStatus(String status);

    long countByDeadlineBefore(LocalDate date);

    // ✅ Extra useful (assigned user)
    List<Task> findByAssignedTo(Long userId);
}