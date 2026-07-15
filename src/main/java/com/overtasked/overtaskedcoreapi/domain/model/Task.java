package com.overtasked.overtaskedcoreapi.domain.model;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;
import com.overtasked.overtaskedcoreapi.domain.exception.TaskAlreadyCompletedException;

import java.time.LocalDateTime;

public class Task {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Integer priority;
    private Long creatorId;
    private Long assigneeId;
    private Long projectId;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(Long id, String title, String description, TaskStatus status, Integer priority, Long creatorId, Long assigneeId, Long projectId, LocalDateTime dueDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.creatorId = creatorId;
        this.assigneeId = assigneeId;
        this.projectId = projectId;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void assignTo(Long userId) {
        this.assigneeId = userId;
    }

    public void complete() {
        if (this.status == TaskStatus.COMPLETED) {
            throw new TaskAlreadyCompletedException("The task has already been completed");
        }

        this.status = TaskStatus.COMPLETED;
    }

    public void reopen() {
        this.status = TaskStatus.TO_DO;
    }
}
