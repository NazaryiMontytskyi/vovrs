package com.dnvr.vovrs.service;

import com.dnvr.vovrs.auxillary_api.validations.Validatable;
import com.dnvr.vovrs.auxillary_api.validations.impl.ClubCreationRequestValidator;
import com.dnvr.vovrs.dto.ClubCreationRequestBody;
import com.dnvr.vovrs.model.Club;
import com.dnvr.vovrs.model.ClubCreationRequest;
import com.dnvr.vovrs.model.User;
import com.dnvr.vovrs.repository.ClubCreationRequestRepository;
import com.dnvr.vovrs.repository.ClubRepository;
import com.dnvr.vovrs.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class ClubService {

    private ClubRepository clubRepository;
    private ClubCreationRequestRepository clubCreationRequestRepository;
    private UserRepository userRepository;


    public ClubCreationRequest createClubCreationRequest(User user, ClubCreationRequestBody requestBody) {
        ClubCreationRequest request = new ClubCreationRequest();
        request.setName(requestBody.name());
        request.setClubDirection(requestBody.direction());
        request.setCreator(user);

        if(requestBody.coLeadersIds() != null && !requestBody.coLeadersIds().isEmpty()) {
            var coleaders = requestBody.coLeadersIds().stream().map(userId -> userRepository.findById(userId).orElseThrow())
                    .collect(Collectors.toSet());
            request.setCoLeader(coleaders);
        }

        clubCreationRequestRepository.save(request);
        return request;
    }

    public List<Club> getAllClubs(){
        return clubRepository.findAll();
    }

    public List<ClubCreationRequest> getAllClubCreateRequests(){
        return clubCreationRequestRepository.findAll();
    }
}
