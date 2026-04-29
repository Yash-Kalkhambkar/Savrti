package com.savrti.plantexchange.dto;

import com.savrti.plantexchange.entity.Request;
import com.savrti.plantexchange.entity.RequestStatus;

import java.time.LocalDateTime;

public class RequestResponseDTO {

    private Long id;
    private PlantResponseDTO plant;
    private UserResponseDTO requester;
    private RequestStatus requestStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RequestResponseDTO() {}

    public RequestResponseDTO(Request request) {
        this.id            = request.getId();
        this.plant         = new PlantResponseDTO(request.getPlant());
        this.requester     = new UserResponseDTO(request.getRequester());
        this.requestStatus = request.getRequestStatus();
        this.createdAt     = request.getCreatedAt();
        this.updatedAt     = request.getUpdatedAt();
    }

    public Long getId() { return id; }
    public PlantResponseDTO getPlant() { return plant; }
    public UserResponseDTO getRequester() { return requester; }
    public RequestStatus getRequestStatus() { return requestStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
