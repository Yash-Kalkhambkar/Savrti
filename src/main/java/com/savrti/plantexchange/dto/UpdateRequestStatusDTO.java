package com.savrti.plantexchange.dto;

import com.savrti.plantexchange.entity.RequestStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateRequestStatusDTO {

    @NotNull(message = "Status is required")
    private RequestStatus status;

    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
}
