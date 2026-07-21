package com.overtasked.overtaskedcoreapi.application.useCase;

import com.overtasked.overtaskedcoreapi.domain.exception.ProjectNotFoundException;
import com.overtasked.overtaskedcoreapi.domain.exception.UserNotFoundException;
import com.overtasked.overtaskedcoreapi.domain.model.Task;
import com.overtasked.overtaskedcoreapi.domain.policy.TaskCreationPolicy;
import com.overtasked.overtaskedcoreapi.domain.port.in.createTask.CreateTaskCommand;
import com.overtasked.overtaskedcoreapi.domain.port.in.createTask.CreateTaskResult;
import com.overtasked.overtaskedcoreapi.domain.port.in.createTask.CreateTaskUseCase;
import com.overtasked.overtaskedcoreapi.domain.port.out.ProjectRepository;
import com.overtasked.overtaskedcoreapi.domain.port.out.TaskRepository;
import com.overtasked.overtaskedcoreapi.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    private final TaskCreationPolicy taskCreationPolicy;

    public CreateTaskUseCaseImpl(
            TaskRepository taskRepository,
            UserRepository userRepository,
            ProjectRepository projectRepository,
            TaskCreationPolicy taskCreationPolicy
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;

        this.taskCreationPolicy = taskCreationPolicy;
    }

    @Override
    public CreateTaskResult execute(CreateTaskCommand command) {
        projectRepository
                .findById(command.projectId())
                .orElseThrow(() -> new ProjectNotFoundException("Error while creating task: Project Not Found"));
        userRepository
                .findById(command.creatorId())
                .orElseThrow(() -> new UserNotFoundException("Error while creating task: User Not Found"));
        taskCreationPolicy
                .validateUserBelongsToProject(command.projectId(), command.creatorId());

        Task task = new Task(
                UUID.randomUUID(),
                command.projectId(),
                command.creatorId(),
                command.assigneeId(),
                command.title(),
                command.description(),
                command.status(),
                command.priority(),
                command.dueDate()
        );
        Task saved = taskRepository.save(task);

        return new CreateTaskResult(saved.getId(), saved.getTitle(), saved.getStatus());
    }
}
