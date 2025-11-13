package com.savrti.plantexchange.service;

import com.savrti.plantexchange.entity.Request;
import com.savrti.plantexchange.repository.RequestRepository;
import com.savrti.plantexchange.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RequestService {
    
    @Autowired
    private RequestRepository requestRepository;
    
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
    
    public Request getRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));
    }
    
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }
    
    public Request updateRequest(Long id, Request requestDetails) {
        Request request = getRequestById(id);
        request.setRequestStatus(requestDetails.getRequestStatus());
        return requestRepository.save(request);
    }
    
    public void deleteRequest(Long id) {
        Request request = getRequestById(id);
        requestRepository.delete(request);
    }
}