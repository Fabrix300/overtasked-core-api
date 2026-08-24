package com.overtasked.overtaskedcoreapi.application.port.out.auth;

import com.overtasked.overtaskedcoreapi.domain.model.user.User;

public interface RefreshTokenGenerator {

    GeneratedRefreshToken generateRefreshToken(User user);

}
