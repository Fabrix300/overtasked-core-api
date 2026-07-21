package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistance.task.repository;

import com.overtasked.overtaskedcoreapi.domain.model.Task;
import com.overtasked.overtaskedcoreapi.domain.port.out.TaskRepository;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistance.task.entity.TaskEntity;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.persistance.task.mapper.TaskPersistenceMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryAdapter implements TaskRepository {

    private final TaskJpaRepository repository;
    private final TaskPersistenceMapper mapper;

    public TaskRepositoryAdapter(
            TaskJpaRepository repository,
            TaskPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Task save(Task task){
        TaskEntity entity = mapper.toEntity(task);
        TaskEntity saved = repository.save(entity);

        return mapper.toDomain(saved);
    }

}
