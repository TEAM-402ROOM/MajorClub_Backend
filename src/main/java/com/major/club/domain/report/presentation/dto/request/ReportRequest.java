package com.major.club.domain.report.presentation.dto.request;

import lombok.Getter;

import java.util.Set;

@Getter
public class ReportRequest {
    private String subject;
    private Set<String> goals;
    private Set<String> techStacks;
    private String progress;
    private String solutions;
    private String feedbackFromMentor;
    private String feedbackFromTeacher;
    private int year;
    private int month;
}
