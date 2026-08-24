package com.overtasked.overtaskedcoreapi.application.port.out.shared;

import java.time.Instant;

public interface Clock {

    Instant now();

}
