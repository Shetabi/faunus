package com.faunus.api.core.watering;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WateringScheduleRepository extends JpaRepository<WateringSchedule, Long> {
    List<WateringSchedule> findAllByOwnerPlantId(Long ownerPlantId);
}
