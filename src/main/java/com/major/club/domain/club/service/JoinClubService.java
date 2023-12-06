package com.major.club.domain.club.service;

import com.major.club.domain.club.domain.Club;
import com.major.club.domain.club.exception.ClubNotFoundException;
import com.major.club.domain.club.presentation.dto.request.ClubRequest;
import com.major.club.domain.club.repository.ClubRepository;
import com.major.club.domain.user.domain.User;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.exception.UserNotFoundException;
import com.major.club.global.jwt.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    public ResponseEntity<String> execute(ClubRequest clubRequest, HttpServletRequest request) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(request))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Club club = clubRepository.findByName(clubRequest.getName()).orElseThrow(
                () -> ClubNotFoundException.EXCEPTION);

        user.setClub(club);
        userRepository.save(user);
        return ResponseEntity.ok("success");
    }
}
