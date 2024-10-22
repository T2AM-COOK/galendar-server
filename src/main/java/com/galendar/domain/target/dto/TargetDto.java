package com.galendar.domain.target.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetDto {
    private Long id; //대상 id
    private String Role; //대상 이름
    private Integer del; //삭제
}