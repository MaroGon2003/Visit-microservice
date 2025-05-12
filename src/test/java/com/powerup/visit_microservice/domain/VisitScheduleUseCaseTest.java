package com.powerup.visit_microservice.domain;

import com.powerup.visit_microservice.domain.exception.*;
import com.powerup.visit_microservice.domain.factory.VisitScheduleTestDataFactory;
import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.domain.spi.IHouseFeignPersistencePort;
import com.powerup.visit_microservice.domain.spi.IJwtInterceptorPort;
import com.powerup.visit_microservice.domain.spi.IUserFeignPersistencePort;
import com.powerup.visit_microservice.domain.spi.IVisitSchedulePersistencePort;
import com.powerup.visit_microservice.domain.usecase.VisitScheduleUseCase;
import com.powerup.visit_microservice.domain.utils.DomainConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitScheduleUseCaseTest {

    @Mock
    private IVisitSchedulePersistencePort visitSchedulePersistencePort;

    @Mock
    private IJwtInterceptorPort jwtInterceptorPort;

    @Mock
    private IUserFeignPersistencePort userFeignPersistencePort;

    @Mock
    private IHouseFeignPersistencePort houseFeignPersistencePort;

    @InjectMocks
    private VisitScheduleUseCase visitScheduleUseCase;

    @Test
    void shouldSetSellerIdSuccessfully() {

        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.visitScheduleModelWithNullSellerId();

        // Arrange
        String email = "test@example.com";
        Long userId = 1L;

        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(userFeignPersistencePort.getUserIdByEmail(email)).thenReturn(userId);
        when(houseFeignPersistencePort.existsHouseById(visitScheduleModel.getHouseId())).thenReturn(true);

        // Act
        visitScheduleUseCase.createVisitSchedule(visitScheduleModel);

        // Assert
        assertEquals(userId, visitScheduleModel.getSellerId());
        verify(jwtInterceptorPort).getEmailFromToken();
        verify(userFeignPersistencePort).getUserIdByEmail(email);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenErrorOccurs() {

        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.visitScheduleModelWithNullSellerId();

        // Arrange
        when(jwtInterceptorPort.getEmailFromToken()).thenThrow(RuntimeException.class);

        // Act & Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> visitScheduleUseCase.createVisitSchedule(visitScheduleModel));
        assertEquals(DomainConstants.USER_NOT_FOUND, exception.getMessage());
        verify(jwtInterceptorPort).getEmailFromToken();
        verifyNoInteractions(userFeignPersistencePort);
    }

    @Test
    void shouldThrowExceptionWhenVisitAlreadyScheduledForHouse() {

        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.validVisitScheduleModel();


        String email = "test@example.com";
        Long userId = 1L;

        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(userFeignPersistencePort.getUserIdByEmail(email)).thenReturn(userId);
        when(visitSchedulePersistencePort.existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId()))
                .thenReturn(true);

        // Act & Assert
        assertThrows(VisitAlreadyScheduledForHouseException.class, () -> visitScheduleUseCase.createVisitSchedule(visitScheduleModel));
        verify(visitSchedulePersistencePort).existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId());
    }

    @Test
    void shouldThrowExceptionWhenStartDateAfterEndDate() {

        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.visitScheduleModelWithStartDateAfterEndDate();

        String email = "test@example.com";
        Long userId = 1L;

        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(userFeignPersistencePort.getUserIdByEmail(email)).thenReturn(userId);
        when(visitSchedulePersistencePort.existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId()))
                .thenReturn(false);

        // Act & Assert
        assertThrows(StartDateAfterEndDateException.class, () -> visitScheduleUseCase.createVisitSchedule(visitScheduleModel));
    }

    @Test
    void shouldThrowExceptionWhenStartDateEqualsEndDate() {
        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.visitScheduleModelWithStartDateEqualsEndDate();

        String email = "test@example.com";
        Long userId = 1L;

        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(userFeignPersistencePort.getUserIdByEmail(email)).thenReturn(userId);
        when(visitSchedulePersistencePort.existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId()))
                .thenReturn(false);

        // Act & Assert
        assertThrows(StartDateEqualsEndDateException.class, () -> visitScheduleUseCase.createVisitSchedule(visitScheduleModel));
    }

    @Test
    void shouldThrowExceptionWhenScheduleOutOfRange() {

        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.validVisitScheduleModel();

        String email = "test@example.com";
        Long userId = 1L;

        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(userFeignPersistencePort.getUserIdByEmail(email)).thenReturn(userId);
        when(visitSchedulePersistencePort.existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId()))
                .thenReturn(false);

        // Arrange
        visitScheduleModel.setStartDateTime(LocalDateTime.now().plusWeeks(4));
        visitScheduleModel.setEndDateTime(LocalDateTime.now().plusWeeks(4).plusHours(1));

        // Act & Assert
        assertThrows(ScheduleOutOfRangeException.class, () -> visitScheduleUseCase.createVisitSchedule(visitScheduleModel));
    }

    @Test
    void shouldThrowExceptionWhenVisitAlreadyScheduledForDateRange() {

        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.validVisitScheduleModel();

        String email = "test@example.com";
        Long userId = 1L;

        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(userFeignPersistencePort.getUserIdByEmail(email)).thenReturn(userId);
        when(visitSchedulePersistencePort.existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId()))
                .thenReturn(false);
        when(visitSchedulePersistencePort.existsBySellerIdAndDateRange(
                visitScheduleModel.getSellerId(),
                visitScheduleModel.getStartDateTime(),
                visitScheduleModel.getEndDateTime())).thenReturn(true);

        // Act & Assert
        assertThrows(VisitAlreadyScheduledForDateRangeException.class, () -> visitScheduleUseCase.createVisitSchedule(visitScheduleModel));
        verify(visitSchedulePersistencePort).existsBySellerIdAndDateRange(
                visitScheduleModel.getSellerId(),
                visitScheduleModel.getStartDateTime(),
                visitScheduleModel.getEndDateTime());
    }

    @Test
    void shouldThrowExceptionWhenHouseNotFound() {

        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.validVisitScheduleModel();

        // Arrange
        when(houseFeignPersistencePort.existsHouseById(visitScheduleModel.getHouseId())).thenReturn(false);

        // Act & Assert
        assertThrows(HouseNotFoundException.class, () -> visitScheduleUseCase.createVisitSchedule(visitScheduleModel));
        verify(houseFeignPersistencePort).existsHouseById(visitScheduleModel.getHouseId());
    }

    @Test
    void shouldCreateVisitScheduleSuccessfully() {

        VisitScheduleModel visitScheduleModel = VisitScheduleTestDataFactory.validVisitScheduleModel();

        // Arrange
        when(jwtInterceptorPort.getEmailFromToken()).thenReturn("test@example.com");
        when(userFeignPersistencePort.getUserIdByEmail("test@example.com")).thenReturn(1L);
        when(houseFeignPersistencePort.existsHouseById(visitScheduleModel.getHouseId())).thenReturn(true);
        when(visitSchedulePersistencePort.existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId()))
                .thenReturn(false);
        when(visitSchedulePersistencePort.existsBySellerIdAndDateRange(
                visitScheduleModel.getSellerId(),
                visitScheduleModel.getStartDateTime(),
                visitScheduleModel.getEndDateTime())).thenReturn(false);

        // Act
        visitScheduleUseCase.createVisitSchedule(visitScheduleModel);

        // Assert
        verify(visitSchedulePersistencePort).createVisitSchedule(visitScheduleModel);
    }

}
