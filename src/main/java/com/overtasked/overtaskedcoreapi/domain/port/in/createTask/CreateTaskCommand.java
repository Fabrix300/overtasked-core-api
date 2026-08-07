package com.overtasked.overtaskedcoreapi.domain.port.in.createTask;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskPriority;
import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskCommand(
        UUID projectId,
        UUID creatorId,
        UUID assigneeId,

        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        Instant dueDate
) {}
