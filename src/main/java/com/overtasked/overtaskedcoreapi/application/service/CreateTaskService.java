package com.overtasked.overtaskedcoreapi.application.service;

import com.overtasked.overtaskedcoreapi.application.port.in.task.createTask.CreateTaskUseCase;
import com.overtasked.overtaskedcoreapi.application.port.out.ProjectMemberRepository;
import com.overtasked.overtaskedcoreapi.application.exception.ProjectNotFoundException;
import com.overtasked.overtaskedcoreapi.application.exception.UserNotFoundException;
import com.overtasked.overtaskedcoreapi.domain.exception.UserNotProjectMemberException;
import com.overtasked.overtaskedcoreapi.domain.model.Project;
import com.overtasked.overtaskedcoreapi.domain.model.ProjectMember;
import com.overtasked.overtaskedcoreapi.domain.model.Task;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import com.overtasked.overtaskedcoreapi.domain.policy.TaskCreationPolicy;
import com.overtasked.overtaskedcoreapi.application.port.in.task.createTask.CreateTaskCommand;
import com.overtasked.overtaskedcoreapi.application.port.in.task.createTask.CreateTaskResult;
import com.overtasked.overtaskedcoreapi.application.port.out.ProjectRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.TaskRepository;
import com.overtasked.overtaskedcoreapi.application.port.out.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CreateTaskService implements CreateTaskUseCase {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    private final TaskCreationPolicy taskCreationPolicy;

    public CreateTaskService(
            TaskRepository taskRepository,
            UserRepository userRepository,
            ProjectRepository projectRepository,
            ProjectMemberRepository projectMemberRepository,
            TaskCreationPolicy taskCreationPolicy
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;

        this.taskCreationPolicy = taskCreationPolicy;
    }

    @Override
    public CreateTaskResult execute(CreateTaskCommand command) {
        Project project = projectRepository
                .findById(command.projectId())
                .orElseThrow(() -> new ProjectNotFoundException("Error while creating task: Project Not Found"));
        User user = userRepository
                .findById(command.creatorId())
                .orElseThrow(() -> new UserNotFoundException("Error while creating task: User Not Found"));
        ProjectMember member = projectMemberRepository
                .findByProjectIdAndUserId(project.getId(), user.getId())
                .orElseThrow(() ->
                        new UserNotProjectMemberException(
                                String.format(
                                        "Error while creating task: User %s is not member of project %s",
                                        user.getId(),
                                        project.getId())));
        taskCreationPolicy.validate(project, member);

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
