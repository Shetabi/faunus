package com.faunus.api.adapter.controller;

import com.faunus.api.core.watering.WateringScheduleService;
import com.faunus.api.core.watering.WateringSubscriptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("owners/{ownerId}/watering-notifications/{plantId}")
public class WateringNotificationController {


    private final WateringScheduleService wateringScheduleService;

    @GetMapping
    public ResponseEntity<SubscriptionStatusDTO> getSubscriptionStatus(@PathVariable("ownerId") Long ownerId, @PathVariable("plantId") Long plantId) {
        WateringSubscriptionStatus status = wateringScheduleService.getSubscriptionStatus(ownerId, plantId);

        return ResponseEntity.ok(new SubscriptionStatusDTO(status));
    }

    @PostMapping
    public ResponseEntity<Void> subscribeNotification(@PathVariable("ownerId") Long ownerId,
                                                      @PathVariable("plantId") Long plantId,
                                                      @RequestBody NotificationRequest request) {
        wateringScheduleService.subscribe(ownerId, plantId, request.token());

        return ResponseEntity
                .created(URI.create("/owners/%s/watering-notifications/%s".formatted(ownerId, plantId)))
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unsubscribeNotification(@PathVariable("ownerId") Long ownerId,
                                                        @PathVariable("plantId") Long plantId) {
        wateringScheduleService.unsubscribe(ownerId, plantId);

        return ResponseEntity.noContent().build();
    }

    public record SubscriptionStatusDTO(WateringSubscriptionStatus status) {
    }

    public record NotificationRequest(String token) {
    }
}

