package com.galendar.domain.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class BookmarkResponse {
    private Long id;
    private Long contestId;
    private String title;
    private String link;
    private String imgLink;
    private LocalDate submitStartDate;
    private LocalDate submitEndDate;
    private LocalDate contestStartDate;
    private LocalDate contestEndDate;
}
