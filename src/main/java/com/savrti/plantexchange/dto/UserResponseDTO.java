package com.savrti.plantexchange.dto;

import com.savrti.plantexchange.entity.User;
import com.savrti.plantexchange.entity.UserType;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private UserType userType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDTO() {}

    public UserResponseDTO(User user) {
        this.id        = user.getId();
        this.name      = user.getName();
        this.email     = user.getEmail();
        this.userType  = user.getUserType();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public UserType getUserType() { return userType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
