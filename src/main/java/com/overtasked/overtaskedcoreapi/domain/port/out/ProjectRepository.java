package com.overtasked.overtaskedcoreapi.domain.port.out;

import com.overtasked.overtaskedcoreapi.domain.model.Project;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {

    Optional<Project> findById(UUID projectId);

}
