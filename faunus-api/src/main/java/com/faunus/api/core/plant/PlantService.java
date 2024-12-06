package com.faunus.api.core.plant;

import com.faunus.api.adapter.controller.PlantDTO;
import com.faunus.api.adapter.controller.WateringMethodDTO;
import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import com.faunus.api.core.watering.WateringMethod;
import com.faunus.api.core.watering.WateringMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository repository;
    private final WateringMethodRepository wateringMethodRepository;
    private final OwnerPlantRepository ownerPlantRepository;

    public List<PlantDTO> getAll() {
        return repository.findAll()
                .stream().map(plant -> {
                    WateringMethod emptyWateringMethod = wateringMethodRepository.findById(plant.getWateringMethodId())
                            .orElse(new WateringMethod(null, null, null, null));

                    return new PlantDTO(plant.getId(), plant.getName(),
                            new WateringMethodDTO(emptyWateringMethod.getTitle(), emptyWateringMethod.getDescription()));
                }).toList();
    }

    public List<PlantDTO> findOwnerPlants(Long ownerId) {
        List<OwnerPlant> plants = ownerPlantRepository.findByOwnerId(ownerId);
        return repository.findAllById(plants.stream().map(OwnerPlant::getPlantId).toList()).stream().map(plant -> {
            WateringMethod emptyWateringMethod = wateringMethodRepository.findById(plant.getWateringMethodId())
                    .orElse(new WateringMethod(null, null, null, null));

            return new PlantDTO(plant.getId(), plant.getName(),
                    new WateringMethodDTO(emptyWateringMethod.getTitle(), emptyWateringMethod.getDescription()));
        }).toList();
    }
}
