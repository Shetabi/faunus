package com.faunus.api.core.watering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WateringLogRepository extends JpaRepository<WateringLog, Long> {

    Optional<WateringLog> findFirstWateringLogsByOwnerPlantIdAndTypeOrderByCreatedOnDesc(@Param("ownerPlantId") Long ownerPlantId,
                                                                                         @Param("type") WateringEventType type);
}
