package com.fynd.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fynd.exception.enums.ErrorCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreditSystemException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public CreditSystemException(Exception exception) {
        super(exception);
        errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        message = exception.getMessage();
    }

    public CreditSystemException(String message, ErrorCode errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public CreditSystemException(Exception exception, ErrorCode errorCode) {
        this(exception);
        this.errorCode = errorCode;
    }

    public CreditSystemException(Exception exception, String message, ErrorCode errorCode) {
        this(exception);
        this.message = message;
        this.errorCode = errorCode;
    }
}
