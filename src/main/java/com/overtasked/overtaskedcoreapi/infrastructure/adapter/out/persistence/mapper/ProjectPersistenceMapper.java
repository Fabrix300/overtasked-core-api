package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper;

import com.overtasked.overtaskedcoreapi.domain.model.Project;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectPersistenceMapper {

    public Project toDomain(ProjectEntity projectEntity) {
        return new Project(
                projectEntity.getId(),
                projectEntity.getName(),
                projectEntity.getDescription()
        );
    }

}
