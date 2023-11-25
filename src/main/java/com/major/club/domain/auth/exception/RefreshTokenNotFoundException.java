package com.major.club.domain.auth.exception;

import com.major.club.global.error.exception.ErrorCode;
import com.major.club.global.error.exception.MajorClubException;

public class RefreshTokenNotFoundException extends MajorClubException {
    public static final RefreshTokenNotFoundException EXCEPTION = new RefreshTokenNotFoundException(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    public RefreshTokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
