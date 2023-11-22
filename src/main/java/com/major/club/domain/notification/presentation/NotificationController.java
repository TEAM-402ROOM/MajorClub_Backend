package com.major.club.domain.notification.presentation;

import com.major.club.domain.notification.presentation.dto.request.PostNotificationRequest;
import com.major.club.domain.notification.service.PostNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NotificationController {

    private final PostNotificationService postNotificationService;

    @PostMapping
    public ResponseEntity<String> postNotification(@RequestBody PostNotificationRequest request) {
        return postNotificationService.execute(request);
    }

}
