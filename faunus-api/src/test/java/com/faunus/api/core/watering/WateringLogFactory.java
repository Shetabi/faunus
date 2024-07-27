package com.faunus.api.core.watering;

import java.time.Instant;

public class WateringLogFactory {

    public static WateringLog of(WateringEventType type, Instant instant) {
        return new WateringLog(1L, 1L, Instant.now(), type, instant.toString());
    }
}
