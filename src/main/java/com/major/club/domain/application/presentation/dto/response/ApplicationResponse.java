package com.major.club.domain.application.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationResponse {
    private String productName;
    private String url;
    private int count;
    private int price;
    private String usageDescription;
    private String etc;
}
