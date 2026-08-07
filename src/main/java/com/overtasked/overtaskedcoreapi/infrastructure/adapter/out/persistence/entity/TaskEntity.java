package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskPriority;
import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        schema = "core",
        name="task",
        indexes = {
                @Index(name = "idx_task_project", columnList = "project_id"),
                @Index(name = "idx_task_assignee", columnList = "assignee_id"),
                @Index(name = "idx_task_status", columnList = "status")
        }
)
@Getter
@Setter
public class TaskEntity {

    @Id
    UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "project_id",
            nullable = false
    )
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "creator_id",
            nullable = false
    )
    private UserEntity creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "assignee_id"
    )
    private UserEntity assignee;

    @Column(nullable = false, length = 180)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

}
