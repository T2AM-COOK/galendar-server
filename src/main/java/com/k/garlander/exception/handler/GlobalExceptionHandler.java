package com.k.garlander.exception.handler;

import com.k.garlander.exception.JwtSignatureException;
import com.k.garlander.exception.RegisterFormException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(JwtSignatureException.class)
    public ResponseEntity<?> handleJwtSignatureException(JwtSignatureException ex) {
        return new ResponseEntity<>(new ErrorResponse("토큰 생성 실패", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchAlgorithmException(NoSuchAlgorithmException ex) {
        return new ResponseEntity<>(new ErrorResponse("인코딩 실패", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RegisterFormException.class)
    public ResponseEntity<ErrorResponse> handleRegisterFormException(RegisterFormException ex) {
        return new ResponseEntity<>(new ErrorResponse("이메일 오류", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<?> handlePropertyValueException(PropertyValueException ex) {
        return new ResponseEntity<>(new ErrorResponse("필수 항목이 입력되지 않았습니다.", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ErrorResponse("이미 존재하는 데이터입니다.", ex.getMessage()), HttpStatus.CONFLICT);
    }
}