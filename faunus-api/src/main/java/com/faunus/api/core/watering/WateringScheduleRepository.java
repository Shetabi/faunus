package com.faunus.api.core.watering;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WateringScheduleRepository extends JpaRepository<WateringSchedule, Long> {
}
