package com.sector.exception;

import com.sector.response.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorResponse.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(
                errorResponseBuilder(errorResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseWrapper<Object>> handleTravelwayEntityNotFoundException(RuntimeException exception) {
        return new ResponseEntity<>(errorResponseBuilder(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    private static ResponseWrapper<Object> errorResponseBuilder(String message) {
        ResponseWrapper<Object> response = new ResponseWrapper<>();
        response.setCode("01");
        response.setMessage(message);
        response.setTimeStamp(ZonedDateTime.now());
        response.setErrors(Map.of("Message", "BAD_REQUEST"));
        return response;
    }

    private static ResponseWrapper<Object> errorResponseBuilder(Map<String, Object> validation) {
        ResponseWrapper<Object> response = new ResponseWrapper<>();
        response.setCode("01");
        response.setMessage("Input Validation Error");
        response.setErrors(validation);
        return response;
    }
}
