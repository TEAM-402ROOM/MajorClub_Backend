package com.major.club.domain.user.presentation;

import com.major.club.domain.user.presentation.dto.response.UserResponse;
import com.major.club.domain.user.service.GetUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final GetUserService getUserService;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(HttpServletRequest request) {
        return getUserService.execute(request);
    }
}
