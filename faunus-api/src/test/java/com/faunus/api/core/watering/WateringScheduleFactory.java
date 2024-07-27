package com.faunus.api.core.watering;

public class WateringScheduleFactory {

    public static WateringSchedule of(Long id, Integer days) {
        return new WateringSchedule(id, null, days);
    }
}
