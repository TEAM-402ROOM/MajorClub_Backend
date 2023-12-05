package com.major.club.global.jwt.exception;

import com.major.club.global.error.exception.ErrorCode;
import com.major.club.global.error.exception.MajorClubException;

public class UserNotFoundException extends MajorClubException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException(ErrorCode.USER_NOT_FOUND);

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
