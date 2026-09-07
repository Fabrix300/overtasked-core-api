package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.security;

import com.overtasked.overtaskedcoreapi.application.port.out.auth.AccessToken;
import com.overtasked.overtaskedcoreapi.application.port.out.auth.AccessTokenGenerator;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.shared.SystemClockAdapter;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtAccessTokenGeneratorAdapter implements AccessTokenGenerator {

    private final JwtEncoder jwtEncoder;
    private final SystemClockAdapter clock;

    public JwtAccessTokenGeneratorAdapter(
            JwtEncoder jwtEncoder,
            SystemClockAdapter clock
    ) {
        this.jwtEncoder = jwtEncoder;
        this.clock = clock;
    }

    @Override
    public AccessToken generateAccessToken(User user) {
        Instant now = clock.now();
        Instant expiresAt = now.plusSeconds(900);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("overtasked")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(expiresAt) // 15 min // TODO: make 900 a variable, maybe .env
                .build();

        String tokenValue = jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();


        return new AccessToken(
                tokenValue,
                expiresAt
        );
    }

}
