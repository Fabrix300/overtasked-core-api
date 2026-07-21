package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistance.task.repository;

import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistance.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> { }
