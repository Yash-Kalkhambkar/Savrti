package com.savrti.plantexchange.controller;

import com.savrti.plantexchange.entity.Plant;
import com.savrti.plantexchange.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/plants")
@CrossOrigin("*")
public class PlantController {
    
    @Autowired
    private PlantService plantService;
    
    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
        Plant plant = plantService.getPlantById(id);
        return ResponseEntity.ok(plant);
    }
    
    @PostMapping
    public ResponseEntity<Plant> createPlant(@Valid @RequestBody Plant plant) {
        Plant createdPlant = plantService.createPlant(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlant);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @Valid @RequestBody Plant plantDetails) {
        Plant updatedPlant = plantService.updatePlant(id, plantDetails);
        return ResponseEntity.ok(updatedPlant);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
        return ResponseEntity.noContent().build();
    }
}