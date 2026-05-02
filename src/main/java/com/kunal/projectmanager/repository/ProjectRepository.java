package com.kunal.projectmanager.repository;

import com.kunal.projectmanager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}