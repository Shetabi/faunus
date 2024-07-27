package com.faunus.api.core.watering;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WateringScheduleTest {

    @Test
    void wateringScheduleEquals() {
        WateringSchedule one = WateringScheduleFactory.of(1L, 1);
        WateringSchedule other = WateringScheduleFactory.of(1L, 1);
        WateringSchedule another = WateringScheduleFactory.of(2L, 1);

        assertEquals(one, other);
        assertNotEquals(one, another);
    }

    @Test
    void wateringScheduleHashcode() {
        WateringSchedule one = WateringScheduleFactory.of(1L, 1);
        WateringSchedule other = WateringScheduleFactory.of(1L, 1);
        WateringSchedule another = WateringScheduleFactory.of(2L, 1);

        assertEquals(one.hashCode(), other.hashCode());
        assertNotEquals(one.hashCode(), another.hashCode());
    }

}