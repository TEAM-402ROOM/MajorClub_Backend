package com.major.club.domain.club.service;

import com.major.club.domain.club.domain.Club;
import com.major.club.domain.club.presentation.dto.request.ClubRequest;
import com.major.club.domain.club.repository.ClubRepository;
import com.major.club.domain.user.domain.User;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.utils.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<String> execute(ClubRequest clubRequest, HttpServletRequest request) {
        User user = userRepository.findByEmail(jwtProvider.extractEmail(request))
                .orElseThrow(() -> new UsernameNotFoundException("No User"));

        Club club = clubRepository.findByName(clubRequest.getName()).orElseThrow(
                () -> new UsernameNotFoundException("No Club"));

        user.setClub(club);
        userRepository.save(user);
        return ResponseEntity.ok("success");
    }
}
