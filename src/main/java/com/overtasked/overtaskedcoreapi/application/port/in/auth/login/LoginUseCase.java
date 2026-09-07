package com.overtasked.overtaskedcoreapi.application.port.in.auth.login;

public interface LoginUseCase {

    AuthenticationResult execute(LoginCommand command);

}
