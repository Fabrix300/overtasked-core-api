package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.mapper;

import com.overtasked.overtaskedcoreapi.domain.model.Task;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskPersistenceMapper {

    public TaskEntity toEntity(Task task){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(task.getId());
        return taskEntity;
    }

    public Task toDomain(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getProject().getId(),
                entity.getCreator().getId(),
                entity.getAssignee().getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getPriority(),
                entity.getDueDate()
        );
    }

}
