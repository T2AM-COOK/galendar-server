package com.galendar.domain.target.controller;

import com.galendar.domain.target.dto.TargetDto;
import com.galendar.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "target", description = "대회 대상 관련 API")
@Controller
public class TargetController {

    @Operation(summary = "대회 등록", description = "참가 대상 조건을 포함한 대회를 등록합니다.")
    @PostMapping("/target/register")
    @ResponseBody
    public ResponseEntity<ResponseData<String>> registerTarget(@RequestBody TargetDto targetDto) {
        return ResponseEntity.ok(ResponseData.ok("대회가 성공적으로 등록되었습니다."));
    }
}