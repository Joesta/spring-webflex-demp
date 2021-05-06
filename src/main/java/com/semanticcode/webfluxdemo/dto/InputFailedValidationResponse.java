package com.semanticcode.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Joesta on 2021/05/01
 */

@Data
@ToString
public class InputFailedValidationResponse {
    private int errorCode;
    private int input;
    private String message;
}
