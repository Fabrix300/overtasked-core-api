package com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.controller;

import com.overtasked.overtaskedcoreapi.domain.port.in.createTask.CreateTaskResult;
import com.overtasked.overtaskedcoreapi.domain.port.in.createTask.CreateTaskUseCase;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.mapper.CreateTaskMapper;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.request.CreateTaskRequest;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.in.web.response.CreateTaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class CreateTaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final CreateTaskMapper createTaskMapper;

    public CreateTaskController(
            CreateTaskUseCase createTaskUseCase,
            CreateTaskMapper createTaskMapper
    ) {
        this.createTaskUseCase = createTaskUseCase;
        this.createTaskMapper = createTaskMapper;
    }

    @PostMapping
    public ResponseEntity<CreateTaskResponse> create(
            @PathVariable UUID projectId,
            @RequestBody @Valid CreateTaskRequest request
    ) {
        CreateTaskResult result = createTaskUseCase.execute(createTaskMapper.toCommand(projectId, request));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createTaskMapper.toResponse(result));
    }

}