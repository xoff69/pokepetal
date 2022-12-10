package com.xoff.pokepetal.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Long resourceId;
    private String errorCode;
    private HttpStatus status;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

    public BusinessException(Long resourceId, String errorCode, String message) {
        super(message);
        this.resourceId = resourceId;
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String message, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}

