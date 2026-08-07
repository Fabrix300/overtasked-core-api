package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper;

import com.overtasked.overtaskedcoreapi.domain.model.ProjectMember;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.ProjectMemberEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMemberPersistenceMapper {

    public ProjectMember toDomain(ProjectMemberEntity projectMemberEntity) {
        return new ProjectMember(
                projectMemberEntity.getProject().getId(),
                projectMemberEntity.getUser().getId(),
                projectMemberEntity.getRole()
        );
    }

}
