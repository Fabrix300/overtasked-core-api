CREATE TABLE project_member (
    project_id UUID NOT NULL,
    user_id UUID NOT NULL,
    role VARCHAR(30) NOT NULL,
    joined_at TIMESTAMP WITH TIME ZONE NOT NULL,

    PRIMARY KEY(project_id,user_id),

    CONSTRAINT fk_project_member_project
        FOREIGN KEY (project_id)
        REFERENCES project(id),

    CONSTRAINT fk_project_member_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);