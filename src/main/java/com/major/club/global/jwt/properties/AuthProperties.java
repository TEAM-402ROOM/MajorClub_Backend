package com.major.club.global.jwt.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "auth")
@AllArgsConstructor
public class AuthProperties {
    private final String client_id;
    private final String client_secret;
}
