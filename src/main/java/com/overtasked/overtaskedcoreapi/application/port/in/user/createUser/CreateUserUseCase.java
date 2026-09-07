package com.overtasked.overtaskedcoreapi.application.port.in.user.createUser;

public interface CreateUserUseCase {

    UserResult execute(CreateUserCommand command);

}
