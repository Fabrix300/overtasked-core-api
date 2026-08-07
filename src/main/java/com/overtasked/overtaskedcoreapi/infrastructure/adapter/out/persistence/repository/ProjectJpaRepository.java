package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.repository;

import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, UUID> {
}
