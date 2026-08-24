package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence;

import com.overtasked.overtaskedcoreapi.domain.model.ProjectMember;
import com.overtasked.overtaskedcoreapi.application.port.out.ProjectMemberRepository;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper.ProjectMemberPersistenceMapper;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.repository.ProjectMemberJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProjectMemberRepositoryAdapter implements ProjectMemberRepository {

    private final ProjectMemberJpaRepository repository;
    private final ProjectMemberPersistenceMapper mapper;

    public ProjectMemberRepositoryAdapter(
            ProjectMemberJpaRepository repository,
            ProjectMemberPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ProjectMember> findByProjectIdAndUserId(
            UUID projectId,
            UUID userId
    ) {
        return repository.findByProjectIdAndUserId(projectId, userId)
                .map(mapper::toDomain);
    }
}
