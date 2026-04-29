package com.savrti.plantexchange.dto;

import com.savrti.plantexchange.entity.AvailabilityStatus;
import com.savrti.plantexchange.entity.Condition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlantRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Plant type is required")
    private String plantType;

    private String description;

    @NotNull(message = "Condition is required")
    private Condition condition;

    @NotNull(message = "Availability status is required")
    private AvailabilityStatus availabilityStatus;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPlantType() { return plantType; }
    public void setPlantType(String plantType) { this.plantType = plantType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Condition getCondition() { return condition; }
    public void setCondition(Condition condition) { this.condition = condition; }

    public AvailabilityStatus getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
