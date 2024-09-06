package com.faunus.api.core.watering;

public class WateringMethodDataFactory {

    public static WateringMethod newWateringMethod() {
        return new WateringMethod(null, "mkey", "title", "description");
    }
}
