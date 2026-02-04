package com.type404.backend.global.exception.exceptionType;

import com.type404.backend.global.exception.CustomException;
import com.type404.backend.global.exception.ErrorCode;

public class InternalServerErrorException extends CustomException {
    public InternalServerErrorException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}