package com.major.club.domain.club.presentation;

import com.major.club.domain.club.presentation.dto.request.ClubRequest;
import com.major.club.domain.club.service.JoinClubService;
import com.major.club.domain.club.service.MakeClubService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final MakeClubService makeClubService;
    private final JoinClubService joinClubService;

    @PostMapping("/make")
    public ResponseEntity<String> makeClub(@RequestBody ClubRequest postClubRequest) {
        return makeClubService.execute(postClubRequest);
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinClub(@RequestBody ClubRequest clubRequest, HttpServletRequest request) {
        return joinClubService.execute(clubRequest, request);
    }

}
