package com.semanticcode.webfluxdemo.exceptionhandler;

import com.semanticcode.webfluxdemo.dto.InputFailedValidationResponse;
import com.semanticcode.webfluxdemo.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Joesta on 2021/05/01
 */
@ControllerAdvice
public class ExceptionValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex) {
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setInput(ex.getInput());
        response.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(response);

    }

}
