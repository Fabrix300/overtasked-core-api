package com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.response;

import com.overtasked.overtaskedcoreapi.domain.enums.TaskStatus;

import java.util.UUID;

public record CreateTaskResponse(
        UUID id,
        String title,
        TaskStatus status
){}
