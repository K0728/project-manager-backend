package com.kunal.projectmanager.controller;

import com.kunal.projectmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private TaskRepository repository;

    // ✅ Global Dashboard (current working)
    @GetMapping
    public Map<String, Long> getDashboard() {

        long total = repository.count();

        long completed = repository.countByStatus("DONE");

        long pending = repository.countByStatus("TODO") +
                repository.countByStatus("IN_PROGRESS");

        long overdue = repository.countByDeadlineBefore(LocalDate.now());

        Map<String, Long> data = new HashMap<>();
        data.put("totalTasks", total);
        data.put("completedTasks", completed);
        data.put("pendingTasks", pending);
        data.put("overdueTasks", overdue);

        return data;
    }

    // ✅ Project-based Dashboard (NEW 🚀)
    @GetMapping("/project/{projectId}")
    public Map<String, Long> getProjectDashboard(@PathVariable Long projectId) {

        long total = repository.findByProjectId(projectId).size();

        long completed = repository.findByProjectId(projectId)
                .stream()
                .filter(t -> "DONE".equals(t.getStatus()))
                .count();

        long pending = repository.findByProjectId(projectId)
                .stream()
                .filter(t -> "TODO".equals(t.getStatus()) || "IN_PROGRESS".equals(t.getStatus()))
                .count();

        long overdue = repository.findByProjectId(projectId)
                .stream()
                .filter(t -> t.getDeadline() != null && t.getDeadline().isBefore(LocalDate.now()))
                .count();

        Map<String, Long> data = new HashMap<>();
        data.put("totalTasks", total);
        data.put("completedTasks", completed);
        data.put("pendingTasks", pending);
        data.put("overdueTasks", overdue);

        return data;
    }
}