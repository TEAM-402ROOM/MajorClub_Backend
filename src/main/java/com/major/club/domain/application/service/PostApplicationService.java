package com.major.club.domain.application.service;

import com.major.club.domain.application.domain.Application;
import com.major.club.domain.application.presentation.dto.request.ApplicationRequest;
import com.major.club.domain.application.repository.ApplicationRepository;
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
public class PostApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    public ResponseEntity<String> execute(ApplicationRequest applicationRequest, HttpServletRequest httpServletRequest) {
        String email = jwtUtil.extractEmail(httpServletRequest);
        User user = userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        applicationRepository.save(
                Application.builder()
                        .productName(applicationRequest.getProductName())
                        .count(applicationRequest.getCount())
                        .url(applicationRequest.getUrl())
                        .price(applicationRequest.getPrice())
                        .usageDescription(applicationRequest.getUsage())
                        .etc(applicationRequest.getEtc())
                        .user(user)
                        .build()
        );
        return ResponseEntity.ok("success");
    }
}
