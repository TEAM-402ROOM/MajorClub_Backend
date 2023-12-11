package com.major.club.domain.club.presentation;

import com.major.club.domain.club.presentation.dto.request.ClubRequest;
import com.major.club.domain.club.presentation.dto.response.ClubResponse;
import com.major.club.domain.club.service.GetClubListService;
import com.major.club.domain.club.service.JoinClubService;
import com.major.club.domain.club.service.MakeClubService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final MakeClubService makeClubService;
    private final JoinClubService joinClubService;
    private final GetClubListService getClubListService;

    @PostMapping("/make")
    public ResponseEntity<String> makeClub(@RequestBody ClubRequest postClubRequest) {
        return makeClubService.execute(postClubRequest);
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinClub(@RequestBody ClubRequest clubRequest, HttpServletRequest request) {
        return joinClubService.execute(clubRequest, request);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClubResponse>> getClubList() {
        return getClubListService.execute();
    }

}
