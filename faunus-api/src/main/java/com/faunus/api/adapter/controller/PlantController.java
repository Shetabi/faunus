package com.faunus.api.adapter.controller;

import com.faunus.api.core.Plant;
import com.faunus.api.core.PlantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlantController {

    private final PlantService service;

    public PlantController(PlantService service) {
        this.service = service;
    }

    @GetMapping("/plants")
    List<Plant> getPlants() {
        return service.getAll();
    }
}
