package com.major.club.global.jwt.exception;

import com.major.club.global.error.exception.ErrorCode;
import com.major.club.global.error.exception.MajorClubException;

public class LoggedOutAccessTokenException extends MajorClubException {
    public static final LoggedOutAccessTokenException EXCEPTION = new LoggedOutAccessTokenException(ErrorCode.LOGGED_OUT_TOKEN);

    public LoggedOutAccessTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
