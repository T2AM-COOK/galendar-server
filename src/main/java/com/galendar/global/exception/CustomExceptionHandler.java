package com.galendar.global.exception;

import com.galendar.global.exception.response.ErrorResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity handlerCustomException(
            CustomException e
    ) {
        ResponseEntity<ErrorResponse> response = ResponseEntity
                .status(e.getStatus())
                .body(
                        new ErrorResponse(e.getStatus(), e.getMessage())
                );
        return response;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(AuthenticationException e) {
        ResponseEntity<ErrorResponse> response = ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, "아이디 및 비밀번호를 확인해 주세요.")
                );
        return response;
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException e) {
        ResponseEntity<ErrorResponse> response = ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage())
                );
        return response;
    }


    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            InternalAuthenticationServiceException.class
    })
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, message)
                );

    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handlerException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
    }

}
