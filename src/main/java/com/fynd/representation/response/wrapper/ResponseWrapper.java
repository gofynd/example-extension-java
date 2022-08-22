package com.fynd.representation.response.wrapper;

import lombok.Data;

@Data
public class ResponseWrapper {
    private String version;

    private long timestamp;

    private Status status;

    private Object data;

    public enum Status {
        SUCCESS, ERROR;
    }
}
