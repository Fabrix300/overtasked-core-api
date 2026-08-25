package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity;

import com.overtasked.overtaskedcoreapi.domain.enums.ProjectMemberRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(
        schema = "core",
        name = "project_member"
)
@Getter
@Setter
public class ProjectMemberEntity {

    @EmbeddedId
    private ProjectMemberId id;

    @MapsId("projectId")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectEntity project;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectMemberRole role;

    @Column(name = "joined_at", nullable = false)
    private Instant joinedAt;

}
