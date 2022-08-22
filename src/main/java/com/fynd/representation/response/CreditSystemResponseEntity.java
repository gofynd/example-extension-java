package com.fynd.representation.response;

import com.fynd.constant.CreditSystemConstant;
import com.fynd.representation.response.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreditSystemResponseEntity extends ResponseEntity<ResponseWrapper> {
    private CreditSystemResponseEntity(ResponseWrapper body, HttpStatus httpStatus) {
        super(body, httpStatus);
    }

    public static CreditSystemResponseEntity buildSuccessResponse(Object payload) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setData(payload);
        responseWrapper.setTimestamp(new Date().getTime());
        responseWrapper.setVersion(CreditSystemConstant.API_VERSION);
        responseWrapper.setStatus(ResponseWrapper.Status.SUCCESS);
        return new CreditSystemResponseEntity(responseWrapper, HttpStatus.OK);
    }

    public static CreditSystemResponseEntity buildErrorResponse(String message, HttpStatus httpStatus) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(CreditSystemConstant.ERROR_MESSAGE, message);

        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setData(map);
        responseWrapper.setTimestamp(new Date().getTime());
        responseWrapper.setVersion(CreditSystemConstant.API_VERSION);
        responseWrapper.setStatus(ResponseWrapper.Status.ERROR);
        return new CreditSystemResponseEntity(responseWrapper, httpStatus);
    }

}
