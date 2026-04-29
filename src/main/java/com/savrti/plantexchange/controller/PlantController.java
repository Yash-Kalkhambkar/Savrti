package com.savrti.plantexchange.controller;

import com.savrti.plantexchange.dto.PlantRequestDTO;
import com.savrti.plantexchange.dto.PlantResponseDTO;
import com.savrti.plantexchange.entity.AvailabilityStatus;
import com.savrti.plantexchange.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plants")
@CrossOrigin("*")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping
    public Page<PlantResponseDTO> getPlants(
            @RequestParam(required = false) AvailabilityStatus status,
            @RequestParam(required = false) Long userId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        if (status != null) return plantService.getPlantsByStatus(status, pageable);
        if (userId != null) return plantService.getPlantsByUser(userId, pageable);
        return plantService.getAllPlants(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantResponseDTO> getPlantById(@PathVariable Long id) {
        return ResponseEntity.ok(plantService.getPlantById(id));
    }

    @PostMapping
    public ResponseEntity<PlantResponseDTO> createPlant(@Valid @RequestBody PlantRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plantService.createPlant(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantResponseDTO> updatePlant(@PathVariable Long id, @Valid @RequestBody PlantRequestDTO dto) {
        return ResponseEntity.ok(plantService.updatePlant(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
        return ResponseEntity.noContent().build();
    }
}
