package com.savrti.plantexchange.service;

import com.savrti.plantexchange.dto.RequestCreateDTO;
import com.savrti.plantexchange.dto.RequestResponseDTO;
import com.savrti.plantexchange.entity.AvailabilityStatus;
import com.savrti.plantexchange.entity.Plant;
import com.savrti.plantexchange.entity.Request;
import com.savrti.plantexchange.entity.RequestStatus;
import com.savrti.plantexchange.entity.User;
import com.savrti.plantexchange.exception.BusinessRuleException;
import com.savrti.plantexchange.exception.ForbiddenException;
import com.savrti.plantexchange.exception.ResourceNotFoundException;
import com.savrti.plantexchange.repository.PlantRepository;
import com.savrti.plantexchange.repository.RequestRepository;
import com.savrti.plantexchange.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private SecurityUtils securityUtils;

    public List<RequestResponseDTO> getAllRequests() {
        return requestRepository.findAll().stream().map(RequestResponseDTO::new).toList();
    }

    public List<RequestResponseDTO> getRequestsByRequester(Long requesterId) {
        return requestRepository.findByRequesterId(requesterId).stream().map(RequestResponseDTO::new).toList();
    }

    public List<RequestResponseDTO> getRequestsByPlant(Long plantId) {
        return requestRepository.findByPlantId(plantId).stream().map(RequestResponseDTO::new).toList();
    }

    public List<RequestResponseDTO> getRequestsByStatus(RequestStatus status) {
        return requestRepository.findByRequestStatus(status).stream().map(RequestResponseDTO::new).toList();
    }

    public RequestResponseDTO getRequestById(Long id) {
        return new RequestResponseDTO(findRequestOrThrow(id));
    }

    @Transactional
    public RequestResponseDTO createRequest(RequestCreateDTO dto) {
        User currentUser = securityUtils.getCurrentUser();

        Plant plant = plantRepository.findById(dto.getPlantId())
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + dto.getPlantId()));

        if (plant.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {
            throw new BusinessRuleException("Plant is not available for exchange");
        }

        if (plant.getUser().getId().equals(currentUser.getId())) {
            throw new BusinessRuleException("You cannot request your own plant");
        }

        boolean alreadyRequested = requestRepository.findByRequesterId(currentUser.getId())
                .stream()
                .anyMatch(r -> r.getPlant().getId().equals(plant.getId())
                        && r.getRequestStatus() == RequestStatus.PENDING);

        if (alreadyRequested) {
            throw new BusinessRuleException("You already have a pending request for this plant");
        }

        Request request = new Request(plant, currentUser, RequestStatus.PENDING);
        return new RequestResponseDTO(requestRepository.save(request));
    }

    @Transactional
    public RequestResponseDTO updateRequestStatus(Long id, RequestStatus newStatus) {
        Request request = findRequestOrThrow(id);
        User currentUser = securityUtils.getCurrentUser();
        Plant plant = request.getPlant();

        if (!plant.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("Only the plant owner can approve or reject requests");
        }

        if (request.getRequestStatus() != RequestStatus.PENDING) {
            throw new BusinessRuleException("Only PENDING requests can be approved or rejected");
        }

        request.setRequestStatus(newStatus);
        requestRepository.save(request);

        if (newStatus == RequestStatus.APPROVED) {
            plant.setAvailabilityStatus(AvailabilityStatus.UNAVAILABLE);
            plantRepository.save(plant);

            List<Request> otherPending = requestRepository.findByPlantId(plant.getId())
                    .stream()
                    .filter(r -> !r.getId().equals(id) && r.getRequestStatus() == RequestStatus.PENDING)
                    .toList();

            otherPending.forEach(r -> r.setRequestStatus(RequestStatus.REJECTED));
            requestRepository.saveAll(otherPending);
        }

        return new RequestResponseDTO(request);
    }

    @Transactional
    public void deleteRequest(Long id) {
        Request request = findRequestOrThrow(id);
        User currentUser = securityUtils.getCurrentUser();

        if (!request.getRequester().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You can only cancel your own requests");
        }

        requestRepository.delete(request);
    }

    private Request findRequestOrThrow(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));
    }
}
