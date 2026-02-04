package com.type404.backend.global.exception.exceptionType;

import com.type404.backend.global.exception.CustomException;
import com.type404.backend.global.exception.ErrorCode;

public class ServiceUnavailableException extends CustomException {
    public ServiceUnavailableException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
