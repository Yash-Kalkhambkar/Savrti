package com.savrti.plantexchange.controller;

import com.savrti.plantexchange.dto.RequestCreateDTO;
import com.savrti.plantexchange.dto.RequestResponseDTO;
import com.savrti.plantexchange.dto.UpdateRequestStatusDTO;
import com.savrti.plantexchange.entity.RequestStatus;
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
    public List<RequestResponseDTO> getRequests(
            @RequestParam(required = false) Long requesterId,
            @RequestParam(required = false) Long plantId,
            @RequestParam(required = false) RequestStatus status) {

        if (requesterId != null) return requestService.getRequestsByRequester(requesterId);
        if (plantId != null) return requestService.getRequestsByPlant(plantId);
        if (status != null) return requestService.getRequestsByStatus(status);
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponseDTO> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getRequestById(id));
    }

    @PostMapping
    public ResponseEntity<RequestResponseDTO> createRequest(@Valid @RequestBody RequestCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requestService.createRequest(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RequestResponseDTO> updateRequestStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRequestStatusDTO dto) {
        return ResponseEntity.ok(requestService.updateRequestStatus(id, dto.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
