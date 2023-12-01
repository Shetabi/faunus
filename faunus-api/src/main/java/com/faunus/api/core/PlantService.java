package com.faunus.api.core;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository repository;

    public List<Plant> getAll(){
        return repository.findAll();
    }
}
