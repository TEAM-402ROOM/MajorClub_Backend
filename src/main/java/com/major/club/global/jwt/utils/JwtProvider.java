package com.major.club.global.jwt.utils;

import com.major.club.domain.user.domain.User;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.exception.UserNotFoundException;
import com.major.club.global.jwt.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    public String createAccessToken(String email) {
        return createToken(email, jwtProperties.getAccessExp());
    }

    public String createRefreshToken(String code) {
        return createToken(code, jwtProperties.getRefreshExp());
    }

    private String createToken(String email, Long validation) {
        Date now = new Date();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        );

        return Jwts.builder()
                .setSubject(email)
                .claim("AUTHORIZATION_KEY", user.getAuthority())
                .signWith(jwtProperties.getSecretKey(), SignatureAlgorithm.HS256)
                .setExpiration(new Date(now.getTime() + validation))
                .compact();
    }

}
