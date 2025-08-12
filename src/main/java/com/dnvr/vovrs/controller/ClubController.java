package com.dnvr.vovrs.controller;


import com.dnvr.vovrs.dto.ClubCreationRequestBody;
import com.dnvr.vovrs.model.Club;
import com.dnvr.vovrs.model.ClubCreationRequest;
import com.dnvr.vovrs.security.CustomUserDetailsService;
import com.dnvr.vovrs.service.ClubService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
@AllArgsConstructor
public class ClubController {

    private CustomUserDetailsService userService;
    private ClubService clubService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/request_create")
    public ResponseEntity<ClubCreationRequest> requestCreateClub(Authentication authentication, @RequestBody ClubCreationRequestBody requestBody) {
        String userEmail = authentication.getName();
        var creator = userService.getUserByUsername(userEmail);
        System.out.println(requestBody.name());

        if(creator == null) {
            throw new UsernameNotFoundException("User not found");
        }

        var resultingRespond = clubService.createClubCreationRequest(creator, requestBody);
        if(resultingRespond == null) {
            throw new NullPointerException();
        }
        return ResponseEntity.ok(resultingRespond);
    }


    @GetMapping("/all_clubs")
    public ResponseEntity<List<Club>> getAllClubs(){
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all_creation_requests")
    public ResponseEntity<List<ClubCreationRequest>> getAllCreationRequests(){
        return ResponseEntity.ok(clubService.getAllClubCreateRequests());
    }
}
