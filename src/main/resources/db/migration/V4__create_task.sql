CREATE TABLE task (
    id UUID PRIMARY KEY,
    project_id UUID NOT NULL,
    creator_id UUID NOT NULL,
    assignee_id UUID,
    title VARCHAR(180) NOT NULL,
    description TEXT,
    status VARCHAR(30),
    priority VARCHAR(20),
    due_date TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_task_project
        FOREIGN KEY (project_id)
        REFERENCES project(id),

    CONSTRAINT fk_task_creator
        FOREIGN KEY (creator_id)
        REFERENCES users(id),

    CONSTRAINT fk_task_assignee
        FOREIGN KEY (assignee_id)
        REFERENCES users(id)
);

-- index
CREATE INDEX idx_task_project
    ON task(project_id);

CREATE INDEX idx_task_assignee
    ON task(assignee_id);

CREATE INDEX idx_task_status
    ON task(status);