package com.overtasked.overtaskedcoreapi.application.port.in.user.login;

public interface LoginUseCase {

    AuthenticationResult execute(LoginCommand command);

}
