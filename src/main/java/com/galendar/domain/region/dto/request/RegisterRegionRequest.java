package com.galendar.domain.region.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRegionRequest {
    @NotBlank(message = "대상 이름을 입력해 주세요")
    private String name;
}
