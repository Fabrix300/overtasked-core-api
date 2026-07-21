package com.overtasked.overtaskedcoreapi.domain.policy;

import com.overtasked.overtaskedcoreapi.domain.exception.UserNotProjectMemberException;
import com.overtasked.overtaskedcoreapi.domain.port.out.ProjectMemberRepository;

import java.util.UUID;

public class TaskCreationPolicy {

    private final ProjectMemberRepository projectMemberRepository;

    public TaskCreationPolicy(
            ProjectMemberRepository projectMemberRepository
    ) {
        this.projectMemberRepository = projectMemberRepository;
    }

    public void validateUserBelongsToProject(UUID projectId, UUID userId) {
        if (projectMemberRepository.findByProjectIdAndUserId(projectId, userId).isEmpty()) {
            String errorMessageTemplate = "Error while creating task: User %s is not member of project %s";

            throw new UserNotProjectMemberException(String.format(errorMessageTemplate, userId, projectId));
        }
    }

}
