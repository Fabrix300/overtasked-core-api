package com.overtasked.overtaskedcoreapi.domain.port.out;

import com.overtasked.overtaskedcoreapi.domain.model.ProjectMember;

import java.util.Optional;
import java.util.UUID;

public interface ProjectMemberRepository {

    Optional<ProjectMember> findByProjectIdAndUserId(UUID projectId, UUID userId);

}
