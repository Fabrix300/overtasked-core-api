package com.overtasked.overtaskedcoreapi.domain.policy;

import com.overtasked.overtaskedcoreapi.domain.exception.TaskCreationNotAllowedException;
import com.overtasked.overtaskedcoreapi.domain.model.Project;
import com.overtasked.overtaskedcoreapi.domain.model.ProjectMember;

public class TaskCreationPolicy {

//    public void validateUserBelongsToProject(UUID projectId, UUID userId) {
//        if (projectMemberRepository.findByProjectIdAndUserId(projectId, userId).isEmpty()) {
//            String errorMessageTemplate = "Error while creating task: User %s is not member of project %s";
//
//            throw new UserNotProjectMemberException(String.format(errorMessageTemplate, userId, projectId));
//        }
//    }

    public void validate(
            Project project,
            ProjectMember member
    ) {
//        if (project.isArchived()) {
//            throw new ProjectArchivedException();
//        }

        if (!member.canCreateTasks()) {
            throw new TaskCreationNotAllowedException("TaskCreationPolicy error: Member not allowed to create tasks");
        }
    }

}
