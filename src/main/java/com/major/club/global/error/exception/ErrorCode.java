package com.major.club.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    REFRESH_TOKEN_HEADER_NOT_FOUND(400, "Refresh Token Not Found"),
    REFRESH_TOKEN_NOT_FOUND(401, "Refresh Token does not exist");

    private final int status;
    private final String message;
}
