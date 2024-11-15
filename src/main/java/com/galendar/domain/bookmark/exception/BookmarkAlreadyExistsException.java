package com.galendar.domain.bookmark.exception;

import com.galendar.global.exception.CustomException;

public class BookmarkAlreadyExistsException extends CustomException {
    public static final CustomException EXCEPTION = new BookmarkAlreadyExistsException();
    private BookmarkAlreadyExistsException() {
        super(409, "이미 등록된 북마크입니다.");
    }
}