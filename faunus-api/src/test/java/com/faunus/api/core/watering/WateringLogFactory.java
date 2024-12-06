package com.faunus.api.core.watering;

import java.time.Instant;

public class WateringLogFactory {

    public static WateringLog of(WateringEventType type, Instant instant) {
        return new WateringLog(1L, 1L, Instant.now(), type, instant.toString());
    }

    public static WateringLog newLog(Long ownerPlant, WateringEventType type, Instant instant) {
        return new WateringLog(null, ownerPlant, Instant.now(), type, instant.toString());
    }
}
