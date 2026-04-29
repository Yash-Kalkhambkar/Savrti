package com.savrti.plantexchange.dto;

import com.savrti.plantexchange.entity.AvailabilityStatus;
import com.savrti.plantexchange.entity.Condition;
import com.savrti.plantexchange.entity.Plant;

import java.time.LocalDateTime;

public class PlantResponseDTO {

    private Long id;
    private String title;
    private String plantType;
    private String description;
    private Condition condition;
    private AvailabilityStatus availabilityStatus;
    private UserResponseDTO owner;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlantResponseDTO() {}

    public PlantResponseDTO(Plant plant) {
        this.id                 = plant.getId();
        this.title              = plant.getTitle();
        this.plantType          = plant.getPlantType();
        this.description        = plant.getDescription();
        this.condition          = plant.getCondition();
        this.availabilityStatus = plant.getAvailabilityStatus();
        this.owner              = new UserResponseDTO(plant.getUser());
        this.createdAt          = plant.getCreatedAt();
        this.updatedAt          = plant.getUpdatedAt();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getPlantType() { return plantType; }
    public String getDescription() { return description; }
    public Condition getCondition() { return condition; }
    public AvailabilityStatus getAvailabilityStatus() { return availabilityStatus; }
    public UserResponseDTO getOwner() { return owner; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
