package com.fynd.exception;

import com.fynd.constant.CreditSystemConstant;
import com.fynd.representation.response.CreditSystemResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public @ResponseBody CreditSystemResponseEntity handleException(final Exception e) {
        logger.error("Exception occurred: {}", e.getMessage(), e);
        return CreditSystemResponseEntity.buildErrorResponse(CreditSystemConstant.EXCEPTION_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CreditSystemException.class)
    public @ResponseBody CreditSystemResponseEntity handleException(final CreditSystemException e) {
        logger.error("Exception occurred: {}", e.getMessage(), e);
        if (e.getErrorCode().getCode() == 400) {
            return CreditSystemResponseEntity.buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else if (e.getErrorCode().getCode() == 404) {
            return CreditSystemResponseEntity.buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return CreditSystemResponseEntity.buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
