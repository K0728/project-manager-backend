package com.kunal.projectmanager.controller;

import com.kunal.projectmanager.entity.Task;
import com.kunal.projectmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin // 👈 frontend (HTML/JS) calls allow करने के लिए
public class TaskController {

    @Autowired
    private TaskRepository repository;

    // ✅ 1. Get ALL tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // ✅ 2. Create Task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    // ✅ 3. Get tasks by project
    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        return repository.findByProjectId(projectId);
    }

    // ✅ 4. Update Task (NEW FEATURE 🚀)
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updatedTask.getTitle());
        task.setStatus(updatedTask.getStatus());
        task.setDeadline(updatedTask.getDeadline());

        return repository.save(task);
    }

    // ✅ 5. Delete Task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }
}