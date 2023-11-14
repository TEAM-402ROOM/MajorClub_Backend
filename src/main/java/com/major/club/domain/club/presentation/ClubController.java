package com.major.club.domain.club.presentation;

import com.major.club.domain.club.presentation.dto.request.PostClubRequest;
import com.major.club.domain.club.service.MakeClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final MakeClubService makeClubService;

    @PostMapping("/join")
    public ResponseEntity<String> makeClub(@RequestBody PostClubRequest postClubRequest) {
        return makeClubService.execute(postClubRequest);
    }
}
