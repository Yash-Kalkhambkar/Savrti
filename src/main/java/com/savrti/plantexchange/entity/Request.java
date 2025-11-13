package com.savrti.plantexchange.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plant_id")
    @NotNull(message = "Plant is required")
    private Plant plant;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requester_id")
    @NotNull(message = "Requester is required")
    private User requester;
    
    @NotNull(message = "Request status is required")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    
    public Request() {}
    
    public Request(Plant plant, User requester, RequestStatus requestStatus) {
        this.plant = plant;
        this.requester = requester;
        this.requestStatus = requestStatus;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }
    
    public User getRequester() { return requester; }
    public void setRequester(User requester) { this.requester = requester; }
    
    public RequestStatus getRequestStatus() { return requestStatus; }
    public void setRequestStatus(RequestStatus requestStatus) { this.requestStatus = requestStatus; }
}