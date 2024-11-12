package com.galendar.domain.bookmark.exception;

import org.springframework.http.HttpStatus;

public class BookmarkException extends StatusException {
    public BookmarkException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public BookmarkException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
