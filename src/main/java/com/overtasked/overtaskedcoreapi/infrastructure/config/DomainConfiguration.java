package com.overtasked.overtaskedcoreapi.infrastructure.config;

import com.overtasked.overtaskedcoreapi.domain.policy.TaskCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public TaskCreationPolicy taskCreationPolicy() {
        return new TaskCreationPolicy();
    }

}
