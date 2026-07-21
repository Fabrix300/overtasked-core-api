package com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.mapper;

import com.overtasked.overtaskedcoreapi.domain.port.in.createTask.CreateTaskCommand;
import com.overtasked.overtaskedcoreapi.domain.port.in.createTask.CreateTaskResult;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.request.CreateTaskRequest;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.response.CreateTaskResponse;

import java.util.UUID;

public class CreateTaskMapper {

    public CreateTaskCommand toCommand(UUID projectId, CreateTaskRequest request){
        return new CreateTaskCommand(
                projectId,
                request.creatorId(),
                request.assigneeId(),
                request.title(),
                request.description(),
                request.status(),
                request.priority(),
                request.dueDate());
    }

    public CreateTaskResponse toResponse(CreateTaskResult result) {
        return new CreateTaskResponse(
                result.taskId(),
                result.title(),
                result.status());
    }
}
