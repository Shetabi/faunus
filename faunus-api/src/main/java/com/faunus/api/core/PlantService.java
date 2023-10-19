package com.faunus.api.core;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository repository;

    public Plant findDefaultPlant() {
        return repository.findAll().get(0);
    }

}
