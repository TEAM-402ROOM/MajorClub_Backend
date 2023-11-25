package com.major.club.domain.auth.repository;

import com.major.club.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmail(String parsingEmail);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
