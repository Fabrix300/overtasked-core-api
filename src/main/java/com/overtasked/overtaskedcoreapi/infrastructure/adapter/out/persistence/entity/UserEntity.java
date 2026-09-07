package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity;

import com.overtasked.overtaskedcoreapi.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        schema = "core",
        name = "users"
//        indexes = {
//                @Index(name = "idx_user_email", columnList = "email")
//        }
)
@Getter
@Setter
public class UserEntity {

    @Id
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public UserEntity() { }

}
