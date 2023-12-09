package com.sector.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Map;

@Data
public class ResponseWrapper<T> {

    private String code = "00";

    private String message = "Successful";

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private ZonedDateTime timeStamp;

    private T data;

    private Map<String, Object> errors;

    public ResponseWrapper() {
    }

    public ResponseWrapper(T body) {
        setSuccessParams(body);
    }

    public ResponseWrapper(T body, String message) {
        setSuccessParams(body, message);
    }

    public ResponseWrapper(T body, String message, String code) {
        this.code = code;
        setSuccessParams(body, message);
    }

    private void setSuccessParams(T body, String message) {
        setMessage(message);
        setData(body);
        setErrors(null);
        setTimeStamp(ZonedDateTime.now());
    }

    private void setSuccessParams(T body) {
        setSuccessParams(body, message);
    }
}
