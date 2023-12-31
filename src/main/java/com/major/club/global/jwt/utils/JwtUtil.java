package com.major.club.global.jwt.utils;

import com.major.club.global.jwt.properties.JwtProperties;
import com.major.club.global.security.auth.AuthDetails;
import com.major.club.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");

        if (bearer == null || !bearer.startsWith("Bearer ")) {
            return null;
        }
        return bearer.split(" ")[1].trim();
    }

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = (AuthDetails) authDetailsService.loadUserByUsername(extractEmailFromToken(token));
        return new UsernamePasswordAuthenticationToken(authDetails, token, authDetails.getAuthorities());
    }

    public String extractEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractEmail(HttpServletRequest request) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey())
                .build()
                .parseClaimsJws(request.getHeader("Authorization").split(" ")[1].trim())
                .getBody()
                .getSubject();
    }

}
