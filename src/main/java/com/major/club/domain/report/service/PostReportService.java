package com.major.club.domain.report.service;

import com.major.club.domain.report.domain.Report;
import com.major.club.domain.report.presentation.dto.request.ReportRequest;
import com.major.club.domain.report.repository.ReportRepository;
import com.major.club.domain.user.repository.UserRepository;
import com.major.club.global.jwt.utils.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<String> execute(ReportRequest request, HttpServletRequest httpServletRequest) {

        reportRepository.save(
                Report.builder()
                        .subject(request.getSubject())
                        .goals(request.getGoals())
                        .techStacks(request.getTechStacks())
                        .solutions(request.getSolutions())
                        .feedbackFromMentor(request.getFeedbackFromMentor())
                        .feedbackFromTeacher(request.getFeedbackFromTeacher())
                        .year(request.getYear())
                        .month(request.getMonth())
                        .club(
                                userRepository.findByEmail(
                                jwtProvider.extractEmail(httpServletRequest)).orElseThrow(() -> new UsernameNotFoundException("No user")).getClub())
                        .build()
        );
        return ResponseEntity.ok("success");
    }
}
