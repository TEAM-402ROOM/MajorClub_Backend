package com.major.club.domain.application.service;

import com.major.club.domain.application.domain.Application;
import com.major.club.domain.application.presentation.dto.response.ApplicationResponse;
import com.major.club.domain.application.repository.ApplicationRepository;
import com.major.club.domain.user.domain.User;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.exception.UserNotFoundException;
import com.major.club.global.jwt.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetListApplicationService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    public ResponseEntity<List<ApplicationResponse>> execute(HttpServletRequest request) {
        User user = userRepository.findByEmail(jwtUtil.extractEmail(request)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        List<Application> applicationList = applicationRepository.findAllByUser(user);

        return ResponseEntity.ok(applicationList.stream()
                .map(application -> ApplicationResponse.builder()
                        .productName(application.getProductName())
                        .url(application.getUrl())
                        .price(application.getPrice())
                        .count(application.getCount())
                        .usageDescription(application.getUsageDescription())
                        .etc(application.getEtc())
                        .build())
                .collect(Collectors.toList()));
    }
}
