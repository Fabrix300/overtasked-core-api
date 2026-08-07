package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.repository;

import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.ProjectMemberEntity;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjectMemberJpaRepository extends JpaRepository<ProjectMemberEntity, ProjectMemberId> {

    Optional<ProjectMemberEntity> findByProjectIdAndUserId(UUID projectId, UUID userId);
}
