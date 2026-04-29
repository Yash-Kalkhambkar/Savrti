package com.savrti.plantexchange.repository;

import com.savrti.plantexchange.entity.AvailabilityStatus;
import com.savrti.plantexchange.entity.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    // Pageable versions — used by service layer
    Page<Plant> findByAvailabilityStatus(AvailabilityStatus status, Pageable pageable);
    Page<Plant> findByUserId(Long userId, Pageable pageable);

    // Non-pageable versions — still used by RequestService for business rule checks
    List<Plant> findByAvailabilityStatus(AvailabilityStatus status);
    List<Plant> findByUserId(Long userId);
}
