package com.major.club.domain.report.presentation.dto.response;

import com.major.club.domain.club.domain.Club;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReportResponse {
    private String subject;
    private List<String> goals;
    private List<String> techStacks;
    private String progress;
    private String solutions;
    private String feedbackFromMentor;
    private String feedbackFromTeacher;
    private int year;
    private int month;
    private Club club;
}
