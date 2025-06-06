package com.powerup.visit_microservice.domain;

import com.powerup.visit_microservice.domain.exception.*;
import com.powerup.visit_microservice.domain.factory.VisitScheduleTestDataFactory;
import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.domain.model.VisitScheduleRequestModel;
import com.powerup.visit_microservice.domain.spi.IHouseFeignPersistencePort;
import com.powerup.visit_microservice.domain.spi.IJwtInterceptorPort;
import com.powerup.visit_microservice.domain.spi.IUserFeignPersistencePort;
import com.powerup.visit_microservice.domain.spi.IVisitSchedulePersistencePort;
import com.powerup.visit_microservice.domain.usecase.VisitScheduleUseCase;
import com.powerup.visit_microservice.domain.utils.DomainConstants;
import com.powerup.visit_microservice.domain.utils.PaginationValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void shouldReturnVisitSchedulesByEndDateTime() {
        // Arrange
        LocalDateTime endDateTime = LocalDateTime.now();
        int page = 0, size = 10;
        List<VisitScheduleModel> expectedSchedules = List.of(new VisitScheduleModel());
        when(visitSchedulePersistencePort.getVisitSchedulesByEndDateTime(endDateTime, page, size))
                .thenReturn(Optional.of(expectedSchedules));

        // Act
        List<VisitScheduleModel> result = visitScheduleUseCase.getVisitSchedule(page, size, null, endDateTime);

        // Assert
        assertEquals(expectedSchedules, result);
        verify(visitSchedulePersistencePort).getVisitSchedulesByEndDateTime(endDateTime, page, size);
    }

    @Test
    void shouldReturnVisitSchedulesByStartDateTime() {
        // Arrange
        LocalDateTime startDateTime = LocalDateTime.now();
        int page = 0, size = 10;
        List<VisitScheduleModel> expectedSchedules = List.of(new VisitScheduleModel());
        when(visitSchedulePersistencePort.getVisitSchedulesByStartDateTime(startDateTime, page, size))
                .thenReturn(Optional.of(expectedSchedules));

        // Act
        List<VisitScheduleModel> result = visitScheduleUseCase.getVisitSchedule(page, size, startDateTime, null);

        // Assert
        assertEquals(expectedSchedules, result);
        verify(visitSchedulePersistencePort).getVisitSchedulesByStartDateTime(startDateTime, page, size);
    }

    @Test
    void shouldReturnVisitSchedulesByDateRange() {
        // Arrange
        LocalDateTime startDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime endDateTime = LocalDateTime.now();
        int page = 0, size = 10;
        List<VisitScheduleModel> expectedSchedules = List.of(new VisitScheduleModel());
        when(visitSchedulePersistencePort.getVisitSchedulesByDateRange(startDateTime, endDateTime, page, size))
                .thenReturn(Optional.of(expectedSchedules));

        // Act
        List<VisitScheduleModel> result = visitScheduleUseCase.getVisitSchedule(page, size, startDateTime, endDateTime);

        // Assert
        assertEquals(expectedSchedules, result);
        verify(visitSchedulePersistencePort).getVisitSchedulesByDateRange(startDateTime, endDateTime, page, size);
    }

    @Test
    void shouldThrowExceptionWhenStartDateAfterEndDate2() {
        // Arrange
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.now().minusDays(1);
        int page = 0, size = 10;

        // Act & Assert
        StartDateAfterEndDateException exception = assertThrows(
                StartDateAfterEndDateException.class,
                () -> visitScheduleUseCase.getVisitSchedule(page, size, startDateTime, endDateTime)
        );
        assertEquals(DomainConstants.START_DATE_AFTER_END_DATE, exception.getMessage());
    }

    @Test
    void shouldReturnAllVisitSchedulesWhenNoDatesProvided() {
        // Arrange
        int page = 0, size = 10;
        List<VisitScheduleModel> expectedSchedules = List.of(new VisitScheduleModel());
        when(visitSchedulePersistencePort.getAllVisitSchedules(page, size))
                .thenReturn(Optional.of(expectedSchedules));

        // Act
        List<VisitScheduleModel> result = visitScheduleUseCase.getVisitSchedule(page, size, null, null);

        // Assert
        assertEquals(expectedSchedules, result);
        verify(visitSchedulePersistencePort).getAllVisitSchedules(page, size);
    }

    @Test
    void shouldReturnEmptyListWhenNoVisitSchedulesFound() {
        // Arrange
        int page = 0, size = 10;
        when(visitSchedulePersistencePort.getAllVisitSchedules(page, size))
                .thenReturn(Optional.empty());

        // Act
        List<VisitScheduleModel> result = visitScheduleUseCase.getVisitSchedule(page, size, null, null);

        // Assert
        assertTrue(result.isEmpty());
        verify(visitSchedulePersistencePort).getAllVisitSchedules(page, size);
    }

    //new tests

    @Test
    void shouldThrowVisitScheduleNotFoundExceptionWhenVisitScheduleDoesNotExist() {
        // Arrange
        Long visitScheduleId = 1L;
        when(visitSchedulePersistencePort.existsByVisitScheduleId(visitScheduleId)).thenReturn(false);

        // Act & Assert
        VisitScheduleNotFoundException exception = assertThrows(
                VisitScheduleNotFoundException.class,
                () -> visitScheduleUseCase.createVisitRequest(visitScheduleId)
        );
        assertEquals(DomainConstants.SCHEDULE_NOT_FOUND, exception.getMessage());
        verify(visitSchedulePersistencePort).existsByVisitScheduleId(visitScheduleId);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenEmailCannotBeRetrieved() {
        // Arrange
        Long visitScheduleId = 1L;
        when(visitSchedulePersistencePort.existsByVisitScheduleId(visitScheduleId)).thenReturn(true);
        when(jwtInterceptorPort.getEmailFromToken()).thenThrow(RuntimeException.class);

        // Act & Assert
        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> visitScheduleUseCase.createVisitRequest(visitScheduleId)
        );
        assertEquals(DomainConstants.USER_NOT_FOUND, exception.getMessage());
        verify(jwtInterceptorPort).getEmailFromToken();
    }

    @Test
    void shouldThrowVisitAlreadyRequestedExceptionWhenRequestAlreadyExists() {
        // Arrange
        Long visitScheduleId = 1L;
        String email = "test@example.com";
        when(visitSchedulePersistencePort.existsByVisitScheduleId(visitScheduleId)).thenReturn(true);
        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(visitSchedulePersistencePort.existsByVisitScheduleIdAndBuyerEmail(visitScheduleId, email)).thenReturn(true);

        // Act & Assert
        VisitAlreadyRequestedException exception = assertThrows(
                VisitAlreadyRequestedException.class,
                () -> visitScheduleUseCase.createVisitRequest(visitScheduleId)
        );
        assertEquals(DomainConstants.VISIT_ALREADY_REQUESTED, exception.getMessage());
        verify(visitSchedulePersistencePort).existsByVisitScheduleIdAndBuyerEmail(visitScheduleId, email);
    }

    @Test
    void shouldDeactivateVisitScheduleWhenSlotCapacityThresholdIsReached() {
        // Arrange
        Long visitScheduleId = 1L;
        VisitScheduleModel visitScheduleModel = new VisitScheduleModel();
        visitScheduleModel.setAvailable(true);

        when(visitSchedulePersistencePort.existsByVisitScheduleId(visitScheduleId)).thenReturn(true);
        when(jwtInterceptorPort.getEmailFromToken()).thenReturn("test@example.com");
        when(visitSchedulePersistencePort.existsByVisitScheduleIdAndBuyerEmail(visitScheduleId, "test@example.com")).thenReturn(false);
        when(visitSchedulePersistencePort.validateSlotCapacity(visitScheduleId)).thenReturn(DomainConstants.SLOT_CAPACITY_THRESHOLD_DEACTIVATE);
        when(visitSchedulePersistencePort.getVisitScheduleById(visitScheduleId)).thenReturn(visitScheduleModel);

        // Act
        visitScheduleUseCase.createVisitRequest(visitScheduleId);

        // Assert
        assertFalse(visitScheduleModel.isAvailable());
        verify(visitSchedulePersistencePort).createVisitSchedule(visitScheduleModel);
    }

    @Test
    void shouldThrowSlotCapacityExceededExceptionWhenSlotCapacityIsExceeded() {
        // Arrange
        Long visitScheduleId = 1L;
        when(visitSchedulePersistencePort.existsByVisitScheduleId(visitScheduleId)).thenReturn(true);
        when(jwtInterceptorPort.getEmailFromToken()).thenReturn("test@example.com");
        when(visitSchedulePersistencePort.existsByVisitScheduleIdAndBuyerEmail(visitScheduleId, "test@example.com")).thenReturn(false);
        when(visitSchedulePersistencePort.validateSlotCapacity(visitScheduleId)).thenReturn(DomainConstants.SLOT_CAPACITY_THRESHOLD_EXCEEDED);

        // Act & Assert
        SlotCapacityExceededException exception = assertThrows(
                SlotCapacityExceededException.class,
                () -> visitScheduleUseCase.createVisitRequest(visitScheduleId)
        );
        assertEquals(DomainConstants.SLOT_CAPACITY_EXCEEDED, exception.getMessage());
    }

    @Test
    void shouldCreateVisitRequestSuccessfully() {
        // Arrange
        Long visitScheduleId = 1L;
        String email = "test@example.com";
        when(visitSchedulePersistencePort.existsByVisitScheduleId(visitScheduleId)).thenReturn(true);
        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(visitSchedulePersistencePort.existsByVisitScheduleIdAndBuyerEmail(visitScheduleId, email)).thenReturn(false);
        when(visitSchedulePersistencePort.validateSlotCapacity(visitScheduleId)).thenReturn(0);

        // Act
        visitScheduleUseCase.createVisitRequest(visitScheduleId);

        // Assert
        verify(visitSchedulePersistencePort).createVisitRequest(any(VisitScheduleRequestModel.class));
    }

    @Test
    void shouldSaveVisitScheduleRequestSuccessful(){
// Arrange
        Long visitScheduleId = 1L;
        String email = "test@example.com";
        VisitScheduleRequestModel visitScheduleRequestModel = VisitScheduleTestDataFactory.visitScheduleRequestModeWithNullBuyerEmail();
        visitScheduleRequestModel.setVisitScheduleId(visitScheduleId);
        visitScheduleRequestModel.setBuyerEmail(email);

        when(visitSchedulePersistencePort.existsByVisitScheduleId(visitScheduleId)).thenReturn(true);
        when(jwtInterceptorPort.getEmailFromToken()).thenReturn(email);
        when(visitSchedulePersistencePort.existsByVisitScheduleIdAndBuyerEmail(visitScheduleId, email)).thenReturn(false);
        when(visitSchedulePersistencePort.validateSlotCapacity(visitScheduleId)).thenReturn(0);

        // Act
        visitScheduleUseCase.createVisitRequest(visitScheduleId);

        // Assert
        verify(visitSchedulePersistencePort).createVisitRequest(argThat(request ->
                request.getVisitScheduleId().equals(visitScheduleId) &&
                        request.getBuyerEmail().equals(email)
        ));
    }

    @Test
    void shouldThrowExceptionWhenPageIndexIsNegative() {
        // Arrange
        int invalidPage = -1;
        int validSize = 10;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PaginationValidator.validatePaginationParameters(invalidPage, validSize)
        );
        assertEquals(DomainConstants.PAGE_INDEX_NEGATIVE_ERROR, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPageSizeIsZeroOrNegative() {
        // Arrange
        int validPage = 0;
        int invalidSize = 0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PaginationValidator.validatePaginationParameters(validPage, invalidSize)
        );
        assertEquals(DomainConstants.PAGE_SIZE_ZERO_OR_NEGATIVE_ERROR, exception.getMessage());
    }
}
