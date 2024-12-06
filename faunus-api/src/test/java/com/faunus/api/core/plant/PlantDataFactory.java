package com.faunus.api.core.plant;

public class PlantDataFactory {

    public static Plant newPlant(Long wateringMethodId) {
        return new Plant(null, "TestPlant", wateringMethodId);
    }
}
