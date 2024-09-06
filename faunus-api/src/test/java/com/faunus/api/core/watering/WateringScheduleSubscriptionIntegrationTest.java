package com.faunus.api.core.watering;

import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import com.faunus.api.core.owner.OwnerRepository;
import com.faunus.api.core.plant.PlantRepository;
import com.faunus.api.testuitls.IntegrationTestSetupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WateringScheduleSubscriptionIntegrationTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private WateringScheduleRepository repository;

    @Autowired
    private WateringLogRepository wateringLogRepository;

    @Autowired
    private OwnerPlantRepository ownerPlantRepository;

    @Autowired
    private IntegrationTestSetupService setupService;

    @Autowired
    private WateringScheduleService sut;

    @Test
    void withNoLogStatusIsNotSubscribed() {
        //Assume
        OwnerPlant ownerPlant = setupService.setupOwnerPlant();

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.WATERED, Instant.now()));

        //Act
        WateringSubscriptionStatus subscriptionStatus = sut.getSubscriptionStatus(ownerPlant.getOwnerId(), ownerPlant.getPlantId());

        //Assert
        assertThat(subscriptionStatus).isEqualTo(WateringSubscriptionStatus.NOT_SUBSCRIBED);
    }

    @Test
    void whenLastSubscriptionLogIsSubscribedStatusIsSubscribed() {
        OwnerPlant ownerPlant = setupService.setupOwnerPlant();

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.WATERED,
                Instant.now().minus(1, ChronoUnit.MINUTES)));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.SUBSCRIBED, Instant.now()));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.WATERED,
                Instant.now().plus(1, ChronoUnit.MINUTES)));

        //Act
        WateringSubscriptionStatus subscriptionStatus =
                sut.getSubscriptionStatus(ownerPlant.getOwnerId(), ownerPlant.getPlantId());

        //Assert
        assertThat(subscriptionStatus).isEqualTo(WateringSubscriptionStatus.SUBSCRIBED);
    }

    @Test
    void whenLastSubscriptionLogIsUnsubscribeStatusIsNotSubscribed() {
        OwnerPlant ownerPlant = setupService.setupOwnerPlant();

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.WATERED,
                        Instant.now().minus(1, ChronoUnit.MINUTES)));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.SUBSCRIBED, Instant.now()));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.WATERED,
                        Instant.now().plus(1, ChronoUnit.MINUTES)));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.UN_SUBSCRIBED,
                        Instant.now().plus(2, ChronoUnit.MINUTES)));

        //Act
        WateringSubscriptionStatus subscriptionStatus =
                sut.getSubscriptionStatus(ownerPlant.getOwnerId(), ownerPlant.getPlantId());

        //Assert
        assertThat(subscriptionStatus).isEqualTo(WateringSubscriptionStatus.NOT_SUBSCRIBED);
    }

    @Test
    void subscriptionAfterUnSubscriptionWorks() {
        OwnerPlant ownerPlant = setupService.setupOwnerPlant();

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.WATERED,
                        Instant.now().minus(1, ChronoUnit.MINUTES)));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.SUBSCRIBED, Instant.now()));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.WATERED,
                        Instant.now().plus(1, ChronoUnit.MINUTES)));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.UN_SUBSCRIBED,
                        Instant.now().plus(2, ChronoUnit.MINUTES)));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.WATERED,
                        Instant.now().plus(3, ChronoUnit.MINUTES)));

        wateringLogRepository.save(WateringLogFactory
                .newLog(ownerPlant.getId(), WateringEventType.SUBSCRIBED,
                        Instant.now().plus(4, ChronoUnit.MINUTES)));

        //Act
        WateringSubscriptionStatus subscriptionStatus =
                sut.getSubscriptionStatus(ownerPlant.getOwnerId(), ownerPlant.getPlantId());

        //Assert
        assertThat(subscriptionStatus).isEqualTo(WateringSubscriptionStatus.SUBSCRIBED);
    }

}