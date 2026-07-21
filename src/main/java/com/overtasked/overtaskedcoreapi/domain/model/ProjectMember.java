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

}
