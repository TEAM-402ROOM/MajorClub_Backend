package com.major.club.domain.notification.service;

import com.major.club.domain.club.domain.Club;
import com.major.club.domain.notification.domain.Notification;
import com.major.club.domain.notification.presentation.dto.response.NotificationResponse;
import com.major.club.domain.notification.repository.NotificationRepository;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.utils.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetNotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<List<NotificationResponse>> execute(HttpServletRequest request) {
        Club club = userRepository.findByEmail(
                jwtProvider.extractEmail(request)
        ).orElseThrow(() -> new UsernameNotFoundException("No user")).getClub();
        return ResponseEntity.ok(
                notificationRepository.findAllByIsAllStudentTrueOrClub(club.getName())
                        .stream().map((notification -> NotificationResponse.builder()
                                .subject(notification.getSubject())
                                .content(notification.getContent())
                                .date(notification.getDate())
                                .build())).collect(Collectors.toList()));
    }
}
