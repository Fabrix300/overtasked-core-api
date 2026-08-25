package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence;

import com.overtasked.overtaskedcoreapi.domain.model.Project;
import com.overtasked.overtaskedcoreapi.application.port.out.ProjectRepository;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.ProjectEntity;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper.ProjectPersistenceMapper;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.repository.ProjectJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProjectRepositoryAdapter implements ProjectRepository {

    private final ProjectJpaRepository repository;
    private final ProjectPersistenceMapper mapper;

    public ProjectRepositoryAdapter(
            ProjectJpaRepository repository,
            ProjectPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Project> findById(UUID projectId) {
        Optional<ProjectEntity> projectEntity = repository.findById(projectId);

        return projectEntity.map(mapper::toDomain);
    }
}
