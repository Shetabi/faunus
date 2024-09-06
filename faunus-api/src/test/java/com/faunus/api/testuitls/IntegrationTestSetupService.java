package com.faunus.api.testuitls;

import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import com.faunus.api.core.owner.OwnerRepository;
import com.faunus.api.core.plant.PlantRepository;
import com.faunus.api.core.watering.WateringMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.faunus.api.core.owner.OwnerDataFactory.newOwner;
import static com.faunus.api.core.plant.PlantDataFactory.newPlant;
import static com.faunus.api.core.watering.WateringMethodDataFactory.newWateringMethod;

@Component
@RequiredArgsConstructor
public class IntegrationTestSetupService {

    private final OwnerRepository ownerRepository;
    private final PlantRepository plantRepository;
    private final WateringMethodRepository wateringMethodRepository;
    private final OwnerPlantRepository ownerPlantRepository;

    public OwnerPlant setupOwnerPlant() {
        var savedWateringMethod = wateringMethodRepository.save(newWateringMethod());
        var savedOwner = ownerRepository.save(newOwner());
        var savedPlant = plantRepository.save(newPlant(savedWateringMethod.getId()));

        return ownerPlantRepository.save(new OwnerPlant(null, savedOwner.getId(), savedPlant.getId()));


    }
}
