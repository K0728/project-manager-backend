package com.kunal.projectmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String status; // TODO, IN_PROGRESS, DONE

    private LocalDate deadline;

    // ✅ Task → Project
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnoreProperties("tasks") // 🔥 prevent infinite loop
    private Project project;

    // ✅ Task → User
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    @JsonIgnoreProperties("tasks") // 🔥 prevent infinite loop
    private User assignedUser;

    // ---------------- GETTERS & SETTERS ----------------

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}