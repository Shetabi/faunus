package com.faunus.api.core.watering;

public enum WateringEventType {

    /**
     * Owner subscribed on watering notification
     */
    SUBSCRIBED,
    /**
     * Owner notified us, that they watered the plant.
     */
    WATERED,
    /**
     * Owner got notified to water the plant.
     */
    NOTIFIED,
    /**
     * Owner unsubscribed from watering notification
     */
    UN_SUBSCRIBED
}
