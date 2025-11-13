package com.savrti.plantexchange.repository;

import com.savrti.plantexchange.entity.Plant;
import com.savrti.plantexchange.entity.AvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findByAvailabilityStatus(AvailabilityStatus status);
    List<Plant> findByUserId(Long userId);
}