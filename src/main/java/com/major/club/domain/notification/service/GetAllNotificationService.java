package com.major.club.domain.notification.service;

import com.major.club.domain.notification.presentation.dto.response.NotificationResponse;
import com.major.club.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllNotificationService {

    private final NotificationRepository notificationRepository;

    public ResponseEntity<List<NotificationResponse>> execute() {
        return ResponseEntity.ok(notificationRepository.findAllByOrderByDateDesc().stream()
                .map(notification -> NotificationResponse.builder()
                        .subject(notification.getSubject())
                        .content(notification.getContent())
                        .date(notification.getDate())
                        .build())
                .collect(Collectors.toList()));
    }
}
