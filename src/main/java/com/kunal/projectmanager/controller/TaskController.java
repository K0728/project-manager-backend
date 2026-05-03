package com.kunal.projectmanager.controller;

import com.kunal.projectmanager.entity.Task;
import com.kunal.projectmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin // 👈 important for frontend calls
public class TaskController {

    @Autowired
    private TaskRepository repository;

    // ✅ Get ALL tasks (IMPORTANT FIX)
    @GetMapping
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // Create Task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    // Get tasks by project
    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        return repository.findByProjectId(projectId);
    }

    // Delete Task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }
}