package com.faunus.api.core.watering;

import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.faunus.api.core.watering.WateringScheduleFactory.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WateringScheduleSubscriptionTest {

    @Mock
    private WateringScheduleRepository repository;
    @Mock
    private WateringLogRepository wateringLogRepository;

    @Mock
    private OwnerPlantRepository ownerPlantRepository;

    @InjectMocks
    private WateringScheduleService sut;

    @Test
    void scheduleIsNotDueIfLastWateringIsNotRegistered() {
        //given
        when(repository.findAll()).thenReturn(
                List.of(
                        of(1L, 3),
                        of(2L, 3)));

        //when
        List<WateringSchedule> eligibleSchedules =
                sut.findDueSchedules();

        //then
        assertThat(eligibleSchedules).isEmpty();

    }

    @Test
    void scheduleIsDueIfItsNextWateringDay() {
        //given
        when(repository.findAll()).thenReturn(
                List.of(
                        of(1L, 3),
                        of(2L, 6)));
        when(wateringLogRepository.findFirstWateringLogsByOwnerPlantIdAndTypeOrderByCreatedOnDesc(any(), any())).thenReturn(Optional.of(
                WateringLogFactory.of(WateringEventType.WATERED,
                        Instant.now().minus(3L, ChronoUnit.DAYS))
        ));


        //when
        List<WateringSchedule> eligibleSchedules = sut.findDueSchedules();

        //then
        assertThat(eligibleSchedules).hasSize(1);

    }

    @Test
    void scheduleIsDueIfNextWateringDayIsPassed() {
        //given
        when(repository.findAll()).thenReturn(
                List.of(
                        of(1L, 2),
                        of(2L, 6)));
        when(wateringLogRepository.findFirstWateringLogsByOwnerPlantIdAndTypeOrderByCreatedOnDesc(any(), any())).thenReturn(Optional.of(
                WateringLogFactory.of(WateringEventType.WATERED,
                        Instant.now().minus(4L, ChronoUnit.DAYS))
        ));


        //when
        List<WateringSchedule> eligibleSchedules = sut.findDueSchedules();

        //then
        assertThat(eligibleSchedules).hasSize(1);

    }

    @Test
    void whenRegisteredWateringScheduleAndLogIsSaved() {
        when(ownerPlantRepository.findByOwnerIdAndPlantId(any(), any()))
                .thenReturn(new OwnerPlant(1L, 11L, 12L));
        when(wateringLogRepository.save(any()))
                .thenReturn(WateringLogFactory.of(WateringEventType.SUBSCRIBED, Instant.now()));
        when(repository.save(any())).thenReturn(WateringScheduleFactory.of(1L, 3));

        sut.subscribe(1L, 11L, "token");

        verify(wateringLogRepository, times(2)).save(any());
        verify(repository).save(any());
    }

}