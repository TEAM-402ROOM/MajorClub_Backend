package com.major.club.domain.auth.service;

import com.major.club.domain.auth.domain.RefreshToken;
import com.major.club.domain.auth.presentation.dto.response.NewAccessTokenResponse;
import com.major.club.domain.auth.repository.RefreshTokenRepository;
import com.major.club.global.jwt.utils.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<?> execute(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").split(" ")[1].trim();
        String refreshToken = request.getHeader("Authorization-Refresh").split(" ")[1].trim();

        if (refreshTokenRepository.findByAccessToken(accessToken).isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("토큰이 DB에 존재하지 않습니다.");
        }

        String email = jwtProvider.extractEmail(refreshToken);
        Optional<RefreshToken> getToken = refreshTokenRepository.findByEmail(email);

        if (getToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("토큰에서 이메일이 추출되지 않습니다.");
        }

        if (getToken.get().getRefreshToken().equals(refreshToken)) {
            String newAccessToken = jwtProvider.createAccessToken(email);

            RefreshToken updateRefreshToken = getToken.get().update(newAccessToken);

            refreshTokenRepository.save(updateRefreshToken);

            return ResponseEntity.ok(
                    NewAccessTokenResponse.builder()
                            .access_token(updateRefreshToken.getAccessToken())
                            .build()
            );
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("토큰이 올바르지 않습니다.");
    }
}