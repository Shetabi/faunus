package com.faunus.api;

import com.faunus.api.core.watering.WateringMethod;
import com.faunus.api.core.watering.WateringMethodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WateringRepositoryTest extends BaseTestSpringBoot {

    @Autowired
    private WateringMethodRepository sut;


    @Test
    void entityIsMappedCorrectly() {
        WateringMethod wateringMethod = new WateringMethod(null, "test key", "test title", "test description");

        WateringMethod save = sut.save(wateringMethod);

        assertNotNull(save.getId());
    }
}
