package com.major.club.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    REFRESH_TOKEN_HEADER_NOT_FOUND(400, "리프레쉬 토큰이 헤더에 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(401, "리프레쉬 토큰이 DB에 존재하지 않습니다"),
    LOGGED_OUT_TOKEN(403, "로그아웃 되어 사용할 수 없는 토큰 입니다."),
    USER_NOT_FOUND(404, "유저가 DB에 존재하지 않습니다");

    private final int status;
    private final String message;
}
