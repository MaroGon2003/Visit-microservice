package com.powerup.visit_microservice.infrastructure.out.feing.client.impl;

import com.powerup.visit_microservice.infrastructure.exception.HouseFetchFailedException;
import com.powerup.visit_microservice.infrastructure.exception.HouseResponseBodyNullException;
import com.powerup.visit_microservice.domain.spi.IHouseFeignPersistencePort;
import com.powerup.visit_microservice.infrastructure.out.feing.client.IHouseFeignClient;
import com.powerup.visit_microservice.infrastructure.utils.InfrastructureConstants;
import org.springframework.http.ResponseEntity;

public class HouseFeignClient implements IHouseFeignPersistencePort {

    private final IHouseFeignClient client;

    public HouseFeignClient(IHouseFeignClient client) {
        this.client = client;
    }

    @Override
    public boolean existsHouseById(long houseId) {

        ResponseEntity<Boolean> response = client.existsHouseById(houseId);

        if (response.getStatusCode().is2xxSuccessful()) {
            if (response.getBody() != null) {
                return response.getBody();
            } else {
                throw new HouseResponseBodyNullException(InfrastructureConstants.HOUSE_RESPONSE_BODY_NULL);
            }
        } else {
            throw new HouseFetchFailedException(InfrastructureConstants.HOUSE_FETCH_FAILED);
        }

    }
}
