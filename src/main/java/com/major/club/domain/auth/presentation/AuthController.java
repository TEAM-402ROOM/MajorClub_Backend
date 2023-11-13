package com.major.club.domain.auth.presentation;

import com.major.club.domain.auth.presentation.dto.response.TokenResponse;
import com.major.club.domain.auth.service.RefreshTokenService;
import com.major.club.domain.auth.service.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserLoginService userLoginService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/bsm")
    public ResponseEntity<TokenResponse> login(@RequestParam("code") String code) throws BsmOAuthInvalidClientException, IOException, BsmOAuthCodeNotFoundException, BsmOAuthTokenNotFoundException {
        return userLoginService.execute(code);
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        return refreshTokenService.execute(request);
    }
}
