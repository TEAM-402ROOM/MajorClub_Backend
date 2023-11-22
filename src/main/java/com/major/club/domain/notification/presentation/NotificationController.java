package com.major.club.domain.notification.presentation;

import com.major.club.domain.notification.presentation.dto.request.PostNotificationRequest;
import com.major.club.domain.notification.presentation.dto.response.NotificationResponse;
import com.major.club.domain.notification.service.GetNotificationService;
import com.major.club.domain.notification.service.PostNotificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NotificationController {

    private final PostNotificationService postNotificationService;
    private final GetNotificationService getNotificationService;

    @PostMapping
    public ResponseEntity<String> postNotification(@RequestBody PostNotificationRequest request) {
        return postNotificationService.execute(request);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getNotification(HttpServletRequest request) {
        return getNotificationService.execute(request);
    }
}
