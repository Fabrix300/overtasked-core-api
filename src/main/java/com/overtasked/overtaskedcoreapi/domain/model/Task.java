package com.overtasked.overtaskedcoreapi.domain.model;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskPriority;
import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;
import com.overtasked.overtaskedcoreapi.domain.exception.InvalidTaskException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private UUID id;
    private UUID projectId;
    private UUID creatorId;
    private UUID assigneeId;

    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;

    private Instant createdAt;
    private Instant updatedAt;

    public Task(
            UUID id,
            UUID projectId,
            UUID creatorId,
            UUID assigneeId,
            String title,
            String description,
            TaskStatus status,
            TaskPriority priority,
            LocalDateTime dueDate
    ) {
        if(title == null || title.isBlank())
            throw new InvalidTaskException("Error while creating task: Title cannot be null or blank");

        this.id = id;
        this.projectId = projectId;
        this.creatorId = creatorId;
        this.assigneeId = assigneeId;

        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;

        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void assignTo(UUID userId){
        this.assigneeId = userId;
    }

    public void start(){
        status = TaskStatus.IN_PROGRESS;
    }

    public void complete() {
        this.status = TaskStatus.COMPLETED;
    }

    public void reopen() {
        this.status = TaskStatus.TODO;
    }
}
