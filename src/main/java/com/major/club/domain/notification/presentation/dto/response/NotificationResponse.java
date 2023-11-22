package com.major.club.domain.notification.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class NotificationResponse {
    private String subject;
    private LocalDate date;
    private String content;
}
