package com.major.club.domain.report.presentation;

import com.major.club.domain.report.presentation.dto.request.ReportRequest;
import com.major.club.domain.report.presentation.dto.response.ReportResponse;
import com.major.club.domain.report.service.GetReportListService;
import com.major.club.domain.report.service.PostReportService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final PostReportService postReportService;
    private final GetReportListService getReportListService;

    @PostMapping
    public ResponseEntity<String> postReport(@RequestBody ReportRequest request, HttpServletRequest httpServletRequest) {
        return postReportService.execute(request, httpServletRequest);
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getReportList(HttpServletRequest request) {
        return getReportListService.execute(request);
    }
}
