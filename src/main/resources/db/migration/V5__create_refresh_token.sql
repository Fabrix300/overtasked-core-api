CREATE TABLE refresh_token (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    token_hash VARCHAR(64) NOT NULL,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    revoked_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_refresh_token_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- Standard case-sensitive constraint
ALTER TABLE refresh_token ADD CONSTRAINT uq_refresh_token_token_hash UNIQUE (token_hash);

CREATE INDEX idx_refresh_token_user_id ON refresh_token(user_id);

-- CREATE INDEX idx_refresh_token_token_hash ON refresh_token(token_hash);