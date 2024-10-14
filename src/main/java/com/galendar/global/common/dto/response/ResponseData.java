package com.galendar.global.common.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseData<T> {

    private int status;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static class Messages {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "fail";
    }

    public static <T> ResponseData<T> ok(T data, String message) {
        return new ResponseData<>(HttpStatus.OK.value(), message, data);
    }

    public static <T> ResponseData<T> fail(T data, String message) {
        return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), message, data);
    }

    public static <T> ResponseData<T> ok(String message) {
        return new ResponseData<>(HttpStatus.OK.value(), message, null);
    }

    public static <T> ResponseData<T> fail(String message) {
        return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), message, null);
    }

}
