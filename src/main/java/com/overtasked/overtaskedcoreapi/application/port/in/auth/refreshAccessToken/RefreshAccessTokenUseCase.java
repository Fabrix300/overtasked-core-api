package com.overtasked.overtaskedcoreapi.application.port.in.auth.refreshAccessToken;

import com.overtasked.overtaskedcoreapi.application.port.in.auth.login.AuthenticationResult;

public interface RefreshAccessTokenUseCase {

    AuthenticationResult execute(RefreshAccessTokenCommand command);

}
