package com.overtasked.overtaskedcoreapi.application.port.in.user.getUser;

import com.overtasked.overtaskedcoreapi.application.port.in.user.createUser.UserResult;

public interface GetUserUseCase {

    UserResult execute(GetUserQuery query);

}
