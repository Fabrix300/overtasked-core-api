package com.overtasked.overtaskedcoreapi.application.port.in.user.getUser;

import java.util.UUID;

public record GetUserQuery(
        UUID userId
) { }
