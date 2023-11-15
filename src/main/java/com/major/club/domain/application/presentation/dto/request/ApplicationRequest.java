package com.major.club.domain.application.presentation.dto.request;

import lombok.Getter;

@Getter
public class ApplicationRequest {
    private String productName;
    private int count;
    private String url;
    private int price;
    private String usage;
    private String etc;
}
