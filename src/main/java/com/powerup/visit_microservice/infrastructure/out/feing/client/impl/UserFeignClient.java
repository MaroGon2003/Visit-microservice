package com.powerup.visit_microservice.infrastructure.out.feing.client.impl;

import com.powerup.visit_microservice.domain.spi.IUserFeignPersistencePort;
import com.powerup.visit_microservice.infrastructure.exception.UserFetchFailedException;
import com.powerup.visit_microservice.infrastructure.exception.UserResponseBodyNullException;
import com.powerup.visit_microservice.infrastructure.out.feing.client.IUserFeignClient;
import com.powerup.visit_microservice.infrastructure.utils.InfrastructureConstants;
import org.springframework.http.ResponseEntity;

public class UserFeignClient implements IUserFeignPersistencePort {

    private final IUserFeignClient client;

    public UserFeignClient(IUserFeignClient client) {
        this.client = client;
    }

    @Override
    public Long getUserIdByEmail(String email) {
        ResponseEntity<Long> response = client.getUserIdByEmail(email);

        if (response.getStatusCode().is2xxSuccessful()) {
            if (response.getBody() != null) {
                return response.getBody();
            } else {
                throw new UserResponseBodyNullException(InfrastructureConstants.USER_RESPONSE_BODY_NULL);
            }
        } else {
            throw new UserFetchFailedException(InfrastructureConstants.USER_FETCH_FAILED);
        }
    }
}
