package com.savrti.plantexchange.dto;

import jakarta.validation.constraints.NotNull;

public class RequestCreateDTO {

    @NotNull(message = "Plant ID is required")
    private Long plantId;

    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }
}
