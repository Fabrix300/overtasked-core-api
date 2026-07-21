package com.overtasked.overtaskedcoreapi.domain.port.out;

import com.overtasked.overtaskedcoreapi.domain.model.Task;

public interface TaskRepository {

    Task save(Task task);

}
