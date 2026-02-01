package com.type404.backend.global.exception.exceptionType;

import com.type404.backend.global.exception.CustomException;
import com.type404.backend.global.exception.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}