package com.powerup.visit_microservice.infrastructure.out.feing.client;

import com.powerup.visit_microservice.infrastructure.utils.InfrastructureConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = InfrastructureConstants.HOUSE_MICROSERVICE_NAME, url = InfrastructureConstants.HOUSE_MICROSERVICE_URL)
public interface IHouseFeignClient {

    @GetMapping(InfrastructureConstants.HOUSE_EXISTS_ENDPOINT)
    ResponseEntity<Boolean> existsHouseById(@RequestParam(InfrastructureConstants.HOUSE_ID_PARAM) long houseId);

}
