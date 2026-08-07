package com.overtasked.overtaskedcoreapi.domain.model;

import com.overtasked.overtaskedcoreapi.domain.enums.ProjectMemberRole;

import java.time.Instant;
import java.util.UUID;

public class ProjectMember {

    private UUID projectId;
    private UUID userId;
    private ProjectMemberRole role;
    private Instant createdAt;
    private Instant updatedAt;

    public ProjectMember(UUID projectId, UUID userId, ProjectMemberRole role) {
        this.projectId = projectId;
        this.userId = userId;
        this.role = role;

        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
