-- CREATE SCHEMA IF NOT EXISTS core;

CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Standard case-sensitive constraint
ALTER TABLE users ADD CONSTRAINT uk_users_email UNIQUE (email);

-- Creating index on email to speed up queries
CREATE INDEX idx_user_email ON users(email);
