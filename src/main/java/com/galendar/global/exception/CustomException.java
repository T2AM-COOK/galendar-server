package com.galendar.global.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private int status;
    private String message;
}
