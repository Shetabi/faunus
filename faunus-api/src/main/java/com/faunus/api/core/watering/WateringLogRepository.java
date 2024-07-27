package com.faunus.api.core.watering;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WateringLogRepository extends JpaRepository<WateringLog, Long> {
    List<WateringLog> findByOwnerPlantId(Long id);
}
