package com.overtasked.overtaskedcoreapi.application.port.out;

import com.overtasked.overtaskedcoreapi.domain.model.Task;

public interface TaskRepository {

    Task save(Task task);

}
