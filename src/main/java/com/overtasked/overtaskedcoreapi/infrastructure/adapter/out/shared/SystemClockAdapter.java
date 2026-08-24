package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.shared;

import com.overtasked.overtaskedcoreapi.application.port.out.shared.Clock;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SystemClockAdapter implements Clock {

    @Override
    public Instant now() {
        return Instant.now();
    }

}
