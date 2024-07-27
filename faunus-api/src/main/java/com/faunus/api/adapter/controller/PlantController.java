package com.faunus.api.adapter.controller;

import com.faunus.api.core.plant.PlantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlantController {

    private final PlantService service;

    public PlantController(PlantService service) {
        this.service = service;
    }

    @GetMapping("/plants")
    List<PlantDTO> getPlants(@RequestParam Long ownerId) {
        if (ownerId == null) {
            return List.of();
        }
        return service.findOwnerPlants(ownerId);
    }
}
