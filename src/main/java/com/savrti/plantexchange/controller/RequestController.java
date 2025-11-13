package com.savrti.plantexchange.controller;

import com.savrti.plantexchange.entity.Request;
import com.savrti.plantexchange.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin("*")
public class RequestController {
    
    @Autowired
    private RequestService requestService;
    
    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Request request = requestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }
    
    @PostMapping
    public ResponseEntity<Request> createRequest(@Valid @RequestBody Request request) {
        Request createdRequest = requestService.createRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @Valid @RequestBody Request requestDetails) {
        Request updatedRequest = requestService.updateRequest(id, requestDetails);
        return ResponseEntity.ok(updatedRequest);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}