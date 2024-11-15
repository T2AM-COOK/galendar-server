package com.galendar.domain.bookmark.exception;

import com.galendar.global.exception.CustomException;

public class BookmarkNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new BookmarkNotFoundException();

    private BookmarkNotFoundException() {
        super(404, "해당 북마크를 찾을 수 없습니다.");
    }
}