package com.major.club.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MajorClubException extends RuntimeException {
    private final ErrorCode errorCode;
}
