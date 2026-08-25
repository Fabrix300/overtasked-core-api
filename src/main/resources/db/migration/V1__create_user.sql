-- CREATE SCHEMA IF NOT EXISTS core;

CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    name VARCHAR(150) NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Standard case-sensitive constraint
ALTER TABLE users ADD CONSTRAINT uq_users_email UNIQUE (email);

-- Creating index on email to speed up queries (Needed if we wouldn't have the previous constraint unique)
-- CREATE INDEX idx_user_email ON users(email);
