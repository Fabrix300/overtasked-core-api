package com.overtasked.overtaskedcoreapi.domain.model;

import com.overtasked.overtaskedcoreapi.domain.enums.ProjectMemberRole;

import java.time.LocalDateTime;

public class ProjectMember {

    private Long projectId;
    private Long userId;
    private ProjectMemberRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
