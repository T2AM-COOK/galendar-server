package com.galendar.global.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private int status;
    private String message;
}
