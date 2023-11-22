package com.major.club.domain.notification.presentation.dto.request;

import lombok.Getter;

@Getter
public class PostNotificationRequest {
    private String toWho;
    private String subject;
    private String content;
}
