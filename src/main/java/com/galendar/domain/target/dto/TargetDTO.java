package com.galendar.domain.target.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TargetDTO {
    private Long id;
    private String name;
}
