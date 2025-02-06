package com.galendar.domain.region.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RegionDTO {
    private Long id;
    private String name;
}
