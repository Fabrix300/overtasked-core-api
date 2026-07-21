package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistance.task.entity;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskPriority;
import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tasks")
@Getter
@Setter
public class TaskEntity{

    @Id
    UUID id;

    UUID projectId;

    UUID creatorId;

    UUID assigneeId;

    String title;

    String description;

    TaskStatus status;

    TaskPriority priority;

    LocalDateTime dueDate;

}
