package com.major.club.domain.club.exception;

import com.major.club.global.error.exception.ErrorCode;
import com.major.club.global.error.exception.MajorClubException;

public class ClubNotFoundException extends MajorClubException {
    public static final ClubNotFoundException EXCEPTION = new ClubNotFoundException(ErrorCode.CLUB_NOT_FOUND);

    public ClubNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
