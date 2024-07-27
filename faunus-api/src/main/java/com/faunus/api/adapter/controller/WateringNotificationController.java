package com.faunus.api.adapter.controller;

import com.faunus.api.core.watering.WateringScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("watering-notification")
public class WateringNotificationController {


    private final WateringScheduleService wateringScheduleService;

    @PostMapping("/subscribe")
    public ResponseEntity<Void> registerNotification(@RequestBody NotificationRequest request) {
        wateringScheduleService.registerWateringSchedule(request.ownerId(), request.plantId(), request.token());

        return ResponseEntity.ok().build();
    }

    public record NotificationRequest(Long ownerId, Long plantId, String token) {
    }
}

