package com.powerup.visit_microservice.infrastructure.out.jwtinterceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.powerup.visit_microservice.domain.spi.IJwtInterceptorPort;
import com.powerup.visit_microservice.infrastructure.configuration.security.jwt.JwtToken;
import com.powerup.visit_microservice.infrastructure.utils.InfrastructureConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtInterceptorAdapter implements IJwtInterceptorPort {

    private final HttpServletRequest request;
    private final JwtToken jwtToken;

    @Override
    public String getEmailFromToken() {
        String token = request.getHeader(InfrastructureConstants.AUTHORIZATION_HEADER);
        if (token != null && token.startsWith(InfrastructureConstants.BEARER_PREFIX)) {
            token = token.substring(InfrastructureConstants.BEARER_PREFIX.length());
            DecodedJWT decodedJWT = jwtToken.validateToken(token);
            return jwtToken.getSpecificClaim(decodedJWT, InfrastructureConstants.SUBJECT_CLAIM).asString();
        } else {
            throw new IllegalArgumentException(InfrastructureConstants.JWT_MISSING_OR_INVALID);
        }

    }
}
