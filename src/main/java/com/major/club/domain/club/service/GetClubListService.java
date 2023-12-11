package com.major.club.domain.club.service;

import com.major.club.domain.club.domain.Club;
import com.major.club.domain.club.presentation.dto.response.ClubResponse;
import com.major.club.domain.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetClubListService {

    private final ClubRepository clubRepository;

    public ResponseEntity<List<ClubResponse>> execute() {
        List<Club> clubList = clubRepository.findAll();
        return ResponseEntity.ok(clubList.stream().map(
                club -> ClubResponse.builder()
                        .name(club.getName())
                        .build()
        ).collect(Collectors.toList()));
    }
}
