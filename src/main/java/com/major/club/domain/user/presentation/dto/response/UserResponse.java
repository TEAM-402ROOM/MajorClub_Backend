package com.major.club.domain.user.presentation.dto.response;

import com.major.club.domain.club.domain.Club;
import com.major.club.domain.user.domain.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String name;
    private Authority authority;
    private int studentNumber;
    private int grade;
    private int classNumber;
    private boolean isGraduated;
    private String email;
    private Club club;
}
