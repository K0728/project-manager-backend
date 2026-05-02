package com.kunal.projectmanager.controller;

import com.kunal.projectmanager.entity.Project;
import com.kunal.projectmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository repository;

    // Create Project
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return repository.save(project);
    }

    // Get All Projects
    @GetMapping
    public List<Project> getAllProjects() {
        return repository.findAll();
    }
}