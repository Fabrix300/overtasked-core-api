package com.overtasked.overtaskedcoreapi.infrastructure.adapter.out.security;

import com.overtasked.overtaskedcoreapi.application.port.out.auth.AccessTokenGenerator;
import com.overtasked.overtaskedcoreapi.domain.model.user.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtAccessTokenGeneratorAdapter implements AccessTokenGenerator {

    private final JwtEncoder jwtEncoder;

    public JwtAccessTokenGeneratorAdapter(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String generateAccessToken(User user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("overtasked")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(900)) // 15 min
                .build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(claims)
        ).getTokenValue();
    }

}
