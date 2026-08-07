package com.overtasked.overtaskedcoreapi.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Project {

    private UUID id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    public Project(
            UUID id,
            String name,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.description = description;

        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
