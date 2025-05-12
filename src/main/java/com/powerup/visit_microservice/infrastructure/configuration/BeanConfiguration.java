package com.powerup.visit_microservice.infrastructure.configuration;

import com.powerup.visit_microservice.domain.api.IVisitScheduleServicePort;
import com.powerup.visit_microservice.domain.spi.IHouseFeignPersistencePort;
import com.powerup.visit_microservice.domain.spi.IJwtInterceptorPort;
import com.powerup.visit_microservice.domain.spi.IUserFeignPersistencePort;
import com.powerup.visit_microservice.domain.spi.IVisitSchedulePersistencePort;
import com.powerup.visit_microservice.domain.usecase.VisitScheduleUseCase;
import com.powerup.visit_microservice.infrastructure.configuration.security.jwt.JwtToken;
import com.powerup.visit_microservice.infrastructure.out.feing.client.IHouseFeignClient;
import com.powerup.visit_microservice.infrastructure.out.feing.client.IUserFeignClient;
import com.powerup.visit_microservice.infrastructure.out.feing.client.impl.HouseFeignClient;
import com.powerup.visit_microservice.infrastructure.out.feing.client.impl.UserFeignClient;
import com.powerup.visit_microservice.infrastructure.out.jpa.adapter.VisitScheduleJpaAdapter;
import com.powerup.visit_microservice.infrastructure.out.jpa.mapper.IVisitScheduleEntityMapper;
import com.powerup.visit_microservice.infrastructure.out.jpa.repository.IVisitScheduleRepository;
import com.powerup.visit_microservice.infrastructure.out.jwtinterceptor.JwtInterceptorAdapter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IVisitScheduleRepository visitScheduleRepository;
    private final IVisitScheduleEntityMapper visitScheduleEntityMapper;

    private final IUserFeignClient userFeignClient;
    private final IHouseFeignClient houseFeignClient;

    private final HttpServletRequest request;
    private final JwtToken jwtToken;

    @Bean
    public IVisitSchedulePersistencePort visitSchedulePersistencePort() {
        return new VisitScheduleJpaAdapter(visitScheduleEntityMapper, visitScheduleRepository);
    }

    @Bean
    public IJwtInterceptorPort jwtInterceptorPort() {
        return new JwtInterceptorAdapter(request, jwtToken);
    }

    @Bean
    public IVisitScheduleServicePort visitScheduleServicePort() {
        return new VisitScheduleUseCase(visitSchedulePersistencePort(), jwtInterceptorPort(), userFeignPersistencePort(), houseFeignPersistencePort());
    }

    @Bean
    public IUserFeignPersistencePort userFeignPersistencePort() {
        return new UserFeignClient(userFeignClient);
    }

    @Bean
    public IHouseFeignPersistencePort houseFeignPersistencePort() {
        return new HouseFeignClient(houseFeignClient);
    }
}
