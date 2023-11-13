package com.major.club.domain.auth.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewAccessTokenResponse {
    private String access_token;
}
