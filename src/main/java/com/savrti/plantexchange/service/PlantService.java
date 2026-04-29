package com.savrti.plantexchange.service;

import com.savrti.plantexchange.dto.PlantRequestDTO;
import com.savrti.plantexchange.dto.PlantResponseDTO;
import com.savrti.plantexchange.entity.AvailabilityStatus;
import com.savrti.plantexchange.entity.Plant;
import com.savrti.plantexchange.entity.User;
import com.savrti.plantexchange.exception.ForbiddenException;
import com.savrti.plantexchange.exception.ResourceNotFoundException;
import com.savrti.plantexchange.repository.PlantRepository;
import com.savrti.plantexchange.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private SecurityUtils securityUtils;

    public Page<PlantResponseDTO> getAllPlants(Pageable pageable) {
        return plantRepository.findAll(pageable).map(PlantResponseDTO::new);
    }

    public Page<PlantResponseDTO> getPlantsByStatus(AvailabilityStatus status, Pageable pageable) {
        return plantRepository.findByAvailabilityStatus(status, pageable).map(PlantResponseDTO::new);
    }

    public Page<PlantResponseDTO> getPlantsByUser(Long userId, Pageable pageable) {
        return plantRepository.findByUserId(userId, pageable).map(PlantResponseDTO::new);
    }

    public PlantResponseDTO getPlantById(Long id) {
        return new PlantResponseDTO(findPlantOrThrow(id));
    }

    public PlantResponseDTO createPlant(PlantRequestDTO dto) {
        User currentUser = securityUtils.getCurrentUser();
        Plant plant = new Plant(
                dto.getTitle(),
                dto.getPlantType(),
                dto.getDescription(),
                dto.getCondition(),
                dto.getAvailabilityStatus(),
                currentUser
        );
        return new PlantResponseDTO(plantRepository.save(plant));
    }

    public PlantResponseDTO updatePlant(Long id, PlantRequestDTO dto) {
        Plant plant = findPlantOrThrow(id);
        User currentUser = securityUtils.getCurrentUser();

        if (!plant.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You are not the owner of this plant");
        }

        plant.setTitle(dto.getTitle());
        plant.setPlantType(dto.getPlantType());
        plant.setDescription(dto.getDescription());
        plant.setCondition(dto.getCondition());
        plant.setAvailabilityStatus(dto.getAvailabilityStatus());
        return new PlantResponseDTO(plantRepository.save(plant));
    }

    public void deletePlant(Long id) {
        Plant plant = findPlantOrThrow(id);
        User currentUser = securityUtils.getCurrentUser();

        if (!plant.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You are not the owner of this plant");
        }

        plantRepository.delete(plant);
    }

    public Plant findPlantOrThrow(Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + id));
    }
}
