package com.faunus.api.core.watering;

import com.faunus.api.BaseTestSpringBoot;
import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import com.faunus.api.testuitls.IntegrationTestSetupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WateringScheduleUnSubscriptionIntegrationTest extends BaseTestSpringBoot {

    @Autowired
    private WateringScheduleRepository wateringScheduleRepository;

    @Autowired
    private WateringLogRepository wateringLogRepository;

    @Autowired
    private OwnerPlantRepository ownerPlantRepository;

    @Autowired
    private IntegrationTestSetupService setupService;

    @Autowired
    private WateringScheduleService sut;

    @Test
    void whenThereAreNoSubscriptionUnsubscribeDoesNothing() {
        //Assume
        OwnerPlant ownerPlant = setupService.setupOwnerPlant();

        //Act
        sut.unsubscribe(ownerPlant.getOwnerId(), ownerPlant.getPlantId());

        //Assert
        assertThat(wateringLogRepository.findAll()).hasSize(0);
    }

    @Test
    void afterSubscriptionUnSubscribeAddsNewLog() {
        //Assume
        OwnerPlant ownerPlant = setupService.setupOwnerPlant();
        sut.subscribe(ownerPlant.getOwnerId(), ownerPlant.getPlantId(), "token");

        //Act
        sut.unsubscribe(ownerPlant.getOwnerId(), ownerPlant.getPlantId());

        //Assert
        Optional<WateringLog> lastLog = wateringLogRepository.findAll().stream()
                .max(Comparator.comparing(WateringLog::getCreatedOn));
        assertThat(lastLog).isPresent().hasValueSatisfying(log -> {
            assertThat(log.getType()).isEqualTo(WateringEventType.UN_SUBSCRIBED);
        });
    }

    @Test
    void afterUnsubscribeUnsubscribeDoesNothing() {
        //Assume
        OwnerPlant ownerPlant = setupService.setupOwnerPlant();
        sut.subscribe(ownerPlant.getOwnerId(), ownerPlant.getPlantId(), "token");
        sut.unsubscribe(ownerPlant.getOwnerId(), ownerPlant.getPlantId());

        //Act
        sut.unsubscribe(ownerPlant.getOwnerId(), ownerPlant.getPlantId());

        //Assert
        assertThat(wateringLogRepository.findAll()
                .stream()
                .filter(l -> l.getType() == WateringEventType.UN_SUBSCRIBED))
                .hasSize(1);
    }

    @Test
    void afterUnsubscribeWateringScheduleIsDeleted() {
        //Assume
        OwnerPlant ownerPlant = setupService.setupOwnerPlant();
        sut.subscribe(ownerPlant.getOwnerId(), ownerPlant.getPlantId(), "token");
        boolean wateringScheduleExists = wateringScheduleRepository.findAll()
                .stream().anyMatch(s -> s.getOwnerPlantId().equals(ownerPlant.getId()));
        assertTrue(wateringScheduleExists);

        //Act
        sut.unsubscribe(ownerPlant.getOwnerId(), ownerPlant.getPlantId());


        //Assert
        boolean wateringScheduleExistsAfter = wateringScheduleRepository.findAll()
                .stream().anyMatch(s -> s.getOwnerPlantId().equals(ownerPlant.getId()));

        assertFalse(wateringScheduleExistsAfter);

    }

}