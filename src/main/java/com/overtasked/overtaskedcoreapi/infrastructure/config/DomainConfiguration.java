package com.overtasked.overtaskedcoreapi.infrastructure.config;

import com.overtasked.overtaskedcoreapi.domain.policy.TaskCreationPolicy;
import com.overtasked.overtaskedcoreapi.domain.port.out.ProjectMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public TaskCreationPolicy taskCreationPolicy(
            ProjectMemberRepository projectMemberRepository) {
        return new TaskCreationPolicy(projectMemberRepository);
    }

}
