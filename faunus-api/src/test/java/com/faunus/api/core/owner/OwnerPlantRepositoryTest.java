package com.faunus.api.core.owner;

import com.faunus.api.core.plant.Plant;
import com.faunus.api.core.plant.PlantRepository;

import com.faunus.api.core.watering.WateringMethod;
import com.faunus.api.core.watering.WateringMethodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OwnerPlantRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private WateringMethodRepository wateringMethodRepository;

    @Autowired
    private OwnerPlantRepository sut;

    @Test
    void ownerPlantCanBeCreated() {
        var owner = ownerRepository.save(new Owner(null, "owner"));

        var wateringMethod = wateringMethodRepository
                .save(new WateringMethod(null, "test", "test", "test"));

        var plant = plantRepository.save(new Plant(null, "plant name", wateringMethod.getId()));

        var saved = sut.save(new OwnerPlant(null, owner.getId(), plant.getId()));

        assertNotNull(saved.getId());

    }
}