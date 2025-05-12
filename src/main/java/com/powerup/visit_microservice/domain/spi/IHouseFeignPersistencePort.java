package com.powerup.visit_microservice.domain.spi;

public interface IHouseFeignPersistencePort {

    boolean existsHouseById(long houseId);

}
