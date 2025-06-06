package com.powerup.visit_microservice.infrastructure.out.feing.client;

import com.powerup.visit_microservice.infrastructure.utils.InfrastructureConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = InfrastructureConstants.USER_MICROSERVICE_NAME, url = InfrastructureConstants.USER_MICROSERVICE_URL)
public interface IUserFeignClient {

    @GetMapping(InfrastructureConstants.USER_GET_ID_BY_EMAIL_ENDPOINT)
    ResponseEntity<Long> getUserIdByEmail(@RequestParam(InfrastructureConstants.USER_EMAIL_PARAM) String email);

}
