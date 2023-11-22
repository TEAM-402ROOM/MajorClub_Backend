package com.major.club.domain.notification.service;

import com.major.club.domain.notification.domain.Notification;
import com.major.club.domain.notification.presentation.dto.request.PostNotificationRequest;
import com.major.club.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PostNotificationService {

    private final NotificationRepository notificationRepository;

    public ResponseEntity<String> execute(PostNotificationRequest request) {
        if (request.getToWho().equals("학생 전체")) {
            notificationRepository.save(Notification.builder()
                    .subject(request.getSubject())
                    .content(request.getContent())
                    .date(LocalDate.now())
                    .isAllStudent(true)
                    .build());
        } else {
            notificationRepository.save(Notification.builder()
                            .subject(request.getSubject())
                            .content(request.getContent())
                            .date(LocalDate.now())
                            .club(request.getToWho())
                    .build());
        }
        return ResponseEntity.ok("success");
    }
}
