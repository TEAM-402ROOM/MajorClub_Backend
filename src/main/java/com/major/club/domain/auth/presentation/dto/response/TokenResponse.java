package com.major.club.domain.auth.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    private String access_token;
    private String refresh_token;
}
