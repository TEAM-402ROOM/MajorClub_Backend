package com.major.club.domain.auth.service;

import com.major.club.domain.auth.presentation.dto.response.TokenResponse;
import com.major.club.domain.user.domain.User;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.properties.AuthProperties;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final AuthProperties authProperties;
    private final UserRepository userRepository;
    private final SaveRefreshTokenService saveRefreshTokenService;

    public ResponseEntity<TokenResponse> execute(String code) throws BsmOAuthInvalidClientException, IOException, BsmOAuthCodeNotFoundException, BsmOAuthTokenNotFoundException {
        BsmOauth bsmOauth = new BsmOauth(authProperties.getClient_id(), authProperties.getClient_secret());
        String token = bsmOauth.getToken(code);
        BsmUserResource resource = bsmOauth.getResource(token);
        if (userRepository.findByEmail(resource.getEmail()).isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(resource.getEmail())
                            .role(resource.getRole())
                            .classNumber(resource.getStudent().getClassNo())
                            .grade(resource.getStudent().getGrade())
                            .isGraduated(resource.getStudent().getIsGraduate())
                            .name(resource.getTeacher().getName().isEmpty() ? resource.getStudent().getName() : resource.getTeacher().getName())
                            .studentNumber(resource.getStudent().getStudentNo())
                            .build()
            );
        }

        return saveRefreshTokenService.execute(resource.getEmail());
    }
}
