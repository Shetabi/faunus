package com.faunus.api.core.watering;

import com.faunus.api.core.NotFoundException;
import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class WateringScheduleService {

    private final WateringScheduleRepository wateringScheduleRepository;
    private final WateringLogRepository wateringLogRepository;
    private final OwnerPlantRepository ownerPlantRepository;

    public WateringSubscriptionStatus getSubscriptionStatus(Long ownerId, Long plantId) {
        OwnerPlant ownerPlant = ownerPlantRepository.findByOwnerIdAndPlantId(ownerId, plantId);
        return decideSubscription(ownerPlant);
    }

    @Transactional
    public void unsubscribe(Long ownerId, Long plantId) {
        OwnerPlant ownerPlant = ownerPlantRepository.findByOwnerIdAndPlantId(ownerId, plantId);

        if (ownerPlant == null) {
            throw new NotFoundException("No plant found for owner");
        }

        WateringSubscriptionStatus status = decideSubscription(ownerPlant);

        List<WateringSchedule> schedules = wateringScheduleRepository.findAllByOwnerPlantId(ownerPlant.getId());

        schedules.forEach(wateringScheduleRepository::delete);

        if (status == WateringSubscriptionStatus.SUBSCRIBED) {
            wateringLogRepository
                    .save(new WateringLog(null,
                            ownerPlant.getId(),
                            null,
                            WateringEventType.UN_SUBSCRIBED,
                            null));

            log.info("Owner %s unsubscribed to get watering notifications for plant %s".formatted(ownerId, plantId));
        }

    }

    @Transactional
    public void subscribe(Long ownerId, Long plantId, String token) {

        OwnerPlant ownerPlant = ownerPlantRepository.findByOwnerIdAndPlantId(ownerId, plantId);

        if (ownerPlant == null) {
            throw new NotFoundException("No plant found for owner");
        }

        WateringSchedule saved = wateringScheduleRepository.save(new WateringSchedule(null, ownerPlant.getId(), 1));
        log.info("Watering schedule created with id {}", saved.getId());

        WateringLog subRecord = wateringLogRepository
                .save(new WateringLog(null, ownerPlant.getId(), null, WateringEventType.SUBSCRIBED, token));
        log.info("Subscribe log created for owner plant id {} with ID {}", ownerPlant.getId(), subRecord.getId());

        //todo:
        // if following lines, we set watering a day before by default but this is a dubious logic
        // users should enter the watering day themselves, but the feature its not implemented yet.
        WateringLog wateringRecord = wateringLogRepository
                .save(new WateringLog(null, ownerPlant.getId(), null,
                        WateringEventType.WATERED, Instant.now().minus(1, ChronoUnit.DAYS).toString()));

        log.info("Watering log created for owner plant id {} with ID {}", ownerPlant.getId(), wateringRecord.getId());
    }

    public List<WateringSchedule> findDueSchedules() {
        List<WateringSchedule> allSchedules = wateringScheduleRepository.findAll();
        log.info("Found {} schedules to check.", allSchedules.size());

        return allSchedules.stream().filter(this::isDue).toList();
    }

    private WateringSubscriptionStatus decideSubscription(OwnerPlant ownerPlant) {
        Optional<WateringLog> lastSubscribe = wateringLogRepository
                .findFirstWateringLogsByOwnerPlantIdAndTypeOrderByCreatedOnDesc(ownerPlant.getId(), WateringEventType.SUBSCRIBED);
        Optional<WateringLog> lastUnSubscribe = wateringLogRepository
                .findFirstWateringLogsByOwnerPlantIdAndTypeOrderByCreatedOnDesc(ownerPlant.getId(), WateringEventType.UN_SUBSCRIBED);

        return lastSubscribe.map(
                        s -> lastUnSubscribe.map(us -> {
                            if (us.getCreatedOn().isAfter(s.getCreatedOn())) {
                                return WateringSubscriptionStatus.NOT_SUBSCRIBED;
                            } else {
                                return WateringSubscriptionStatus.SUBSCRIBED;
                            }
                        }).orElse(WateringSubscriptionStatus.SUBSCRIBED)
                )
                .orElse(WateringSubscriptionStatus.NOT_SUBSCRIBED);
    }

    private Boolean isDue(WateringSchedule schedule) {
        Optional<WateringLog> lastWatering = wateringLogRepository
                .findFirstWateringLogsByOwnerPlantIdAndTypeOrderByCreatedOnDesc(schedule.getOwnerPlantId(), WateringEventType.WATERED);

        return lastWatering.map(l -> passedDueDate(schedule, Instant.parse(l.getPayload())))
                .orElse(false);
    }

    private boolean passedDueDate(WateringSchedule wateringSchedule, Instant fromDate) {
        Instant next = fromDate.plus(wateringSchedule.getFrequencyDays(), ChronoUnit.DAYS);

        return toDate(Instant.now()).isAfter(toDate(next)) ||
                toDate(Instant.now()).isEqual(toDate(next));
    }

    private LocalDate toDate(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate();
    }
}
