package com.faunus.api.core.watering;

import com.faunus.api.core.NotFoundException;
import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class WateringScheduleService {

    private final WateringScheduleRepository repository;
    private final WateringLogRepository wateringLogRepository;
    private final OwnerPlantRepository ownerPlantRepository;

    @Transactional
    public void registerWateringSchedule(Long ownerId, Long plantId, String token) {

        OwnerPlant ownerPlant = ownerPlantRepository.findByOwnerIdAndPlantId(ownerId, plantId);

        if (ownerPlant == null) {
            throw new NotFoundException("No plant found for owner");
        }

        WateringSchedule saved = repository.save(new WateringSchedule(null, ownerPlant.getId(), 1));
        log.info("Watering schedule created with id {}", saved.getId());

        WateringLog subRecord = wateringLogRepository
                .save(new WateringLog(null, ownerPlant.getId(), null, WateringEventType.SUBSCRIBED, token));
        log.info("Subscribe log created for owner plant id {} with ID {}", ownerPlant.getId(), subRecord.getId());

        WateringLog wateringRecord = wateringLogRepository
                .save(new WateringLog(null, ownerPlant.getId(), null,
                        WateringEventType.WATERED, Instant.now().minus(1, ChronoUnit.DAYS).toString()));
        log.info("Watering log created for owner plant id {} with ID {}", ownerPlant.getId(), wateringRecord.getId());

    }

    public List<WateringSchedule> findDueSchedules() {
        List<WateringSchedule> allSchedules = repository.findAll();
        log.info("Found {} schedules to check.", allSchedules.size());

        return allSchedules.stream().filter(this::isDue).toList();
    }

    private Boolean isDue(WateringSchedule schedule) {
        List<WateringLog> logs = wateringLogRepository.findByOwnerPlantId(schedule.getOwnerPlantId());
        Optional<WateringLog> lastWatering = logs.stream().filter(l -> l.getType().equals(WateringEventType.WATERED))
                .max(Comparator.comparing(WateringLog::getCreatedOn))
                .stream().findFirst();

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
