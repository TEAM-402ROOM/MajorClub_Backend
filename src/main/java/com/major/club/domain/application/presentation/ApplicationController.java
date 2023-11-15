package com.major.club.domain.application.presentation;

import com.major.club.domain.application.presentation.dto.request.ApplicationRequest;
import com.major.club.domain.application.service.PostApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final PostApplicationService postApplicationService;

    @PostMapping
    public ResponseEntity<String> postApplication(@RequestBody ApplicationRequest applicationRequest, HttpServletRequest request) {
        return postApplicationService.execute(applicationRequest, request);
    }
}
