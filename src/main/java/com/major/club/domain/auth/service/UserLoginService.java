package com.major.club.domain.auth.service;

import com.major.club.domain.auth.presentation.dto.response.TokenResponse;
import com.major.club.domain.user.domain.Authority;
import com.major.club.domain.user.domain.User;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.JwtProvider;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    @Value("${bsm.client_id}")
    private String BSM_AUTH_CLIENT_ID;
    @Value("${bsm.client_secret}")
    private String BSM_AUTH_CLIENT_SECRET;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<TokenResponse> execute(String code) throws BsmOAuthInvalidClientException, IOException, BsmOAuthCodeNotFoundException, BsmOAuthTokenNotFoundException {
        BsmOauth bsmOauth = new BsmOauth(BSM_AUTH_CLIENT_ID, BSM_AUTH_CLIENT_SECRET);
        String token = bsmOauth.getToken(code);
        BsmUserResource resource = bsmOauth.getResource(token);
        if (userRepository.findByEmail(resource.getEmail()).isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(resource.getEmail())
                            .authority(resource.getTeacher() == null ? Authority.ROLE_USER : Authority.ROLE_TEACHER)
                            .classNumber(resource.getStudent().getClassNo())
                            .grade(resource.getStudent().getGrade())
                            .isGraduated(resource.getStudent().getIsGraduate())
                            .name(resource.getStudent().getName())
                            .studentNumber(resource.getStudent().getStudentNo())
                            .build()
            );
        }
        String access_token = jwtProvider.createAccessToken(resource.getEmail());
        String refresh_token = jwtProvider.createRefreshToken(resource.getEmail());
        return ResponseEntity.ok(TokenResponse.builder()
                .access_token(access_token)
                .refresh_token(refresh_token)
                .build());
    }
}
