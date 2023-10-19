package com.faunus.api.adapter.controller;

import com.faunus.api.core.Plant;
import com.faunus.api.core.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlantController {

    private final PlantService service;

    @GetMapping("/plant")
    ResponseEntity<Plant> getPlant() {
        return ResponseEntity.ok(service.findDefaultPlant());
    }
}
