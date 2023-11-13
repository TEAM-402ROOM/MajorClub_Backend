package com.major.club.global.jwt;

import com.major.club.domain.user.domain.User;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.auth.details.AuthDetails;
import com.major.club.global.auth.details.AuthDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtProvider implements InitializingBean {

    @Value("${jwt.secret_key}")
    private String secretKey;
    private Key key;

    private final UserRepository userRepository;
    private String AUTHORIZATION_KEY = "majorclubkey";

    private final AuthDetailsService authDetailsService;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String code) {
        return createToken(code, JwtProperties.ACCESS_TOKEN_EXPIRED);
    }

    public String createRefreshToken(String code) {
        return createToken(code, JwtProperties.REFRESH_TOKEN_EXPIRED);
    }

    private String createToken(String code, Long validation) {
        Date now = new Date();
        User user = userRepository.findByEmail(code).orElseThrow(() -> new UsernameNotFoundException("No user"));

        return Jwts.builder()
                .setSubject(code)
                .setIssuedAt(now)
                .claim(AUTHORIZATION_KEY, user.getAuthority())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now.getTime() + validation))
                .compact();
    }

    public String extractEmail(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORIZATION_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();
        AuthDetails authDetails = (AuthDetails) authDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(authDetails, jwt, authorities);
    }
}
