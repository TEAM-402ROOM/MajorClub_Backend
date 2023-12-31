package com.major.club.domain.auth.service;

import com.major.club.domain.auth.exception.RefreshTokenHeaderNotFoundException;
import com.major.club.global.jwt.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final SaveRefreshTokenService saveRefreshTokenService;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> execute(HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization-Refresh");

        if (refreshToken == null) {
            throw RefreshTokenHeaderNotFoundException.EXCEPTION;
        }

        return saveRefreshTokenService.execute(jwtUtil.extractEmailFromToken(refreshToken.split(" ")[1].trim()));
    }
}
