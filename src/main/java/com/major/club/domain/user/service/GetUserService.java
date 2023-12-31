package com.major.club.domain.user.service;

import com.major.club.domain.user.domain.User;
import com.major.club.domain.user.presentation.dto.response.UserResponse;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.exception.UserNotFoundException;
import com.major.club.global.jwt.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    public ResponseEntity<UserResponse> execute(HttpServletRequest request) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(request)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );
        return ResponseEntity.ok(
                UserResponse.builder()
                        .name(user.getName())
                        .role(user.getRole())
                        .classNumber(user.getClassNumber())
                        .club(user.getClub())
                        .grade(user.getGrade())
                        .isGraduated(user.isGraduated())
                        .email(user.getEmail())
                        .studentNumber(user.getStudentNumber())
                        .build()
        );
    }
}
