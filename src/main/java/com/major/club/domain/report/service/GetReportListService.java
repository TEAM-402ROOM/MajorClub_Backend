package com.major.club.domain.report.service;

import com.major.club.domain.club.domain.Club;
import com.major.club.domain.report.domain.Report;
import com.major.club.domain.report.presentation.dto.response.ReportResponse;
import com.major.club.domain.report.repository.ReportRepository;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.exception.UserNotFoundException;
import com.major.club.global.jwt.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetReportListService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<List<ReportResponse>> execute(HttpServletRequest request) {
        Club userClub = userRepository.findByEmail(jwtUtil.extractEmail(request)).orElseThrow(
                () -> UserNotFoundException.EXCEPTION
        ).getClub();
        List<Report> reportList = reportRepository.findAllByClub(userClub);
        return ResponseEntity.ok(
                reportList.stream()
                        .map(report -> ReportResponse.builder()
                                .feedbackFromMentor(report.getFeedbackFromMentor())
                                .feedbackFromTeacher(report.getFeedbackFromTeacher())
                                .year(report.getYear())
                                .month(report.getMonth())
                                .club(report.getClub())
                                .goals(report.getGoals().stream().toList())
                                .subject(report.getSubject())
                                .progress(report.getProgress())
                                .solutions(report.getSolutions())
                                .techStacks(report.getTechStacks().stream().toList())
                                .build()
                        )
                        .collect(Collectors.toList())
        );
    }
}
