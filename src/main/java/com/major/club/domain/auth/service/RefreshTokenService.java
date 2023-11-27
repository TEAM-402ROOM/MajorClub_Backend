package com.major.club.domain.auth.service;

import com.major.club.domain.auth.domain.RefreshToken;
import com.major.club.domain.auth.exception.RefreshTokenHeaderNotFoundException;
import com.major.club.domain.auth.exception.RefreshTokenNotFoundException;
import com.major.club.domain.auth.presentation.dto.response.NewAccessTokenResponse;
import com.major.club.domain.auth.repository.RefreshTokenRepository;
import com.major.club.global.jwt.utils.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<?> execute(HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization-Refresh");

        if (refreshToken == null) {
            throw RefreshTokenHeaderNotFoundException.EXCEPTION;
        }

        refreshToken = refreshToken.split(" ")[1].trim();

        RefreshToken getToken = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> RefreshTokenNotFoundException.EXCEPTION
        );

        String newAccessToken = jwtProvider.createAccessToken(getToken.getEmail());

        RefreshToken updateRefreshToken = getToken.update(newAccessToken);

        refreshTokenRepository.save(updateRefreshToken);

        return ResponseEntity.ok(
                NewAccessTokenResponse.builder()
                        .access_token(updateRefreshToken.getAccessToken())
                        .build()
        );
    }
}
