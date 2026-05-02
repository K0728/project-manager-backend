package com.kunal.projectmanager.controller;

import com.kunal.projectmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private TaskRepository repository;

    @GetMapping
    public Map<String, Long> getDashboard() {

        long total = repository.count();

        long completed = repository.countByStatus("DONE");

        long pending = repository.countByStatus("TODO") +
                repository.countByStatus("IN_PROGRESS");

        String today = LocalDate.now().toString();
        long overdue = repository.countByDeadlineBefore(today);

        Map<String, Long> data = new HashMap<>();
        data.put("totalTasks", total);
        data.put("completedTasks", completed);
        data.put("pendingTasks", pending);
        data.put("overdueTasks", overdue);

        return data;
    }
}