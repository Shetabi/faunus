package com.faunus.api.core.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerPlantRepository extends JpaRepository<OwnerPlant, Long> {
    OwnerPlant findByOwnerIdAndPlantId(Long ownerId, Long plantId);

    List<OwnerPlant> findByOwnerId(Long ownerId);
}
