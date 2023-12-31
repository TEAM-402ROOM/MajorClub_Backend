package com.major.club.domain.auth.service;

import com.major.club.domain.auth.domain.RefreshToken;
import com.major.club.domain.auth.presentation.dto.response.TokenResponse;
import com.major.club.domain.auth.repository.RefreshTokenRepository;
import com.major.club.global.jwt.utils.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<TokenResponse> execute(String email) {
        String access_token = jwtProvider.createAccessToken(email);
        String refresh_token = jwtProvider.createRefreshToken(email);

        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByEmail(email);

        if (optionalRefreshToken.isEmpty()) {
            refreshTokenRepository.save(RefreshToken.builder()
                    .accessToken(access_token)
                    .refreshToken(refresh_token)
                    .email(email)
                    .build());
        } else {
            RefreshToken refreshToken = optionalRefreshToken.get();
            refreshToken.setAccessToken(access_token);
            refreshToken.setRefreshToken(refresh_token);
            refreshTokenRepository.save(refreshToken);
        }

        return ResponseEntity.ok(TokenResponse.builder()
                .access_token(access_token)
                .refresh_token(refresh_token)
                .build());
    }
}
