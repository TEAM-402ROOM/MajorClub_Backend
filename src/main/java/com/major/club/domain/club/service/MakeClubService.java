package com.major.club.domain.club.service;

import com.major.club.domain.club.domain.Club;
import com.major.club.domain.club.presentation.dto.request.ClubRequest;
import com.major.club.domain.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeClubService {

    private final ClubRepository clubRepository;
    public ResponseEntity<String> execute(ClubRequest clubRequest) {
        clubRepository.save(Club.builder()
                .name(clubRequest.getName())
                .build());
        return ResponseEntity.ok("success");
    }
}
