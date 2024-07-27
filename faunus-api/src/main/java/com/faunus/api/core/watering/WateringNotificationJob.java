package com.faunus.api.core.watering;

import com.faunus.api.core.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class WateringNotificationJob {

    private final WateringScheduleService wateringService;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 */1 * * * *")
    public void notifyWatering() {
        log.info("Starting to send notifications.");

        List<WateringSchedule> matchingSchedules = wateringService.findDueSchedules();

        log.info("Found {} matching schedules", matchingSchedules.size());
        matchingSchedules.forEach(schedule ->
            notificationService.notifyOwner(schedule.getOwnerPlantId())
        );

    }
}
