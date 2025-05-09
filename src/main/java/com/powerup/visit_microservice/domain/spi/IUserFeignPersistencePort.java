package com.powerup.visit_microservice.domain.spi;

public interface IUserFeignPersistencePort {

    Long getUserIdByEmail(String email);

}
