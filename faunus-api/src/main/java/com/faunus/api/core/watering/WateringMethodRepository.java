package com.faunus.api.core.watering;

import com.faunus.api.core.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WateringMethodRepository extends JpaRepository<WateringMethod, Long> {
}
