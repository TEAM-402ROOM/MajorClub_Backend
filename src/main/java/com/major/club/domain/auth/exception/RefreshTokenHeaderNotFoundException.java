package com.major.club.domain.auth.exception;

import com.major.club.global.error.exception.ErrorCode;
import com.major.club.global.error.exception.MajorClubException;

public class RefreshTokenHeaderNotFoundException extends MajorClubException {
    public static final RefreshTokenHeaderNotFoundException EXCEPTION = new RefreshTokenHeaderNotFoundException(ErrorCode.REFRESH_TOKEN_HEADER_NOT_FOUND);
    public RefreshTokenHeaderNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
