package com.major.club.domain.application.presentation;

import com.major.club.domain.application.presentation.dto.request.ApplicationRequest;
import com.major.club.domain.application.presentation.dto.response.ApplicationResponse;
import com.major.club.domain.application.service.GetListApplicationService;
import com.major.club.domain.application.service.PostApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final PostApplicationService postApplicationService;
    private final GetListApplicationService getListApplicationService;

    @PostMapping
    @Operation(summary = "물품 신청서 작성 (토큰 필요)")
    public ResponseEntity<String> postApplication(@RequestBody ApplicationRequest applicationRequest, HttpServletRequest request) {
        return postApplicationService.execute(applicationRequest, request);
    }

    @GetMapping("/list")
    @Operation(summary = "물품 신청서 전체 조회 (토큰 필요)")
    public ResponseEntity<List<ApplicationResponse>> getList(HttpServletRequest request) {
        return getListApplicationService.execute(request);
    }

}
