package com.dnvr.vovrs.repository;

import com.dnvr.vovrs.model.ClubCreationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubCreationRequestRepository extends JpaRepository<ClubCreationRequest, Long> {

    Optional<ClubCreationRequest> findById(Long id);
}
