package com.galendar.domain.bookmark.entity;

import java.io.Serializable;
import java.util.Objects;

public class BookmarkId implements Serializable {
    private Long user;
    private Long contest;

    public BookmarkId() {}

    public BookmarkId(Long user, Long contest) {
        this.user = user;
        this.contest = contest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookmarkId that = (BookmarkId) o;
        return Objects.equals(user, that.user) && Objects.equals(contest, that.contest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, contest);
    }
}
