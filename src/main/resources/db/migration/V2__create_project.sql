CREATE TABLE project (
    id UUID PRIMARY KEY,
    owner_id UUID NOT NULL,
    name VARCHAR(120) NOT NULL,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_project_owner
        FOREIGN KEY(owner_id)
        REFERENCES users(id)
);

CREATE INDEX idx_project_owner ON project(owner_id);