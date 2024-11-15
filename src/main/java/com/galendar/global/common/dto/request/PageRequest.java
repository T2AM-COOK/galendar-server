package com.galendar.global.common.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

    private int page;
    private int size;

    public PageRequest() {
        this.page = 1;
        this.size = 10;
    }

}
