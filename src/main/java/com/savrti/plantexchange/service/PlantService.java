package com.savrti.plantexchange.service;

import com.savrti.plantexchange.entity.Plant;
import com.savrti.plantexchange.repository.PlantRepository;
import com.savrti.plantexchange.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlantService {
    
    @Autowired
    private PlantRepository plantRepository;
    
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }
    
    public Plant getPlantById(Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + id));
    }
    
    public Plant createPlant(Plant plant) {
        return plantRepository.save(plant);
    }
    
    public Plant updatePlant(Long id, Plant plantDetails) {
        Plant plant = getPlantById(id);
        plant.setTitle(plantDetails.getTitle());
        plant.setPlantType(plantDetails.getPlantType());
        plant.setDescription(plantDetails.getDescription());
        plant.setCondition(plantDetails.getCondition());
        plant.setAvailabilityStatus(plantDetails.getAvailabilityStatus());
        return plantRepository.save(plant);
    }
    
    public void deletePlant(Long id) {
        Plant plant = getPlantById(id);
        plantRepository.delete(plant);
    }
}