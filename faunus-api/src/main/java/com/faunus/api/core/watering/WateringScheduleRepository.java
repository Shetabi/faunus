package com.faunus.api.core.watering;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WateringScheduleRepository extends JpaRepository<WateringSchedule, Long> {
    Optional<WateringSchedule> findByOwnerPlantId(Long ownerPlantId);
}
