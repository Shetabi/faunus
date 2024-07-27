package com.faunus.api.core.watering;

public enum WateringEventType {

    SUBSCRIBED,
    /**
     * Owner notified us, that they watered the plant.
     */
    WATERED,
    /**
     * Owner got notified to water the plant.
     */
    NOTIFIED
}
