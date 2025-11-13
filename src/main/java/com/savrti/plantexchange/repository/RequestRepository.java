package com.savrti.plantexchange.repository;

import com.savrti.plantexchange.entity.Request;
import com.savrti.plantexchange.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequestStatus(RequestStatus status);
    List<Request> findByRequesterId(Long requesterId);
    List<Request> findByPlantId(Long plantId);
}