package com.galendar.domain.target.controller;

import com.galendar.domain.region.dto.RegionDTO;
import com.galendar.domain.target.dto.TargetDTO;
import com.galendar.domain.target.dto.request.RegisterTargetRequest;
import com.galendar.domain.target.service.TargetService;
import com.galendar.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "대회 대상자", description = "대회 대상자 관련 API (관리자)")
@RestController
@RequestMapping("/target")
@RequiredArgsConstructor
public class TargetController {

    private final TargetService targetService;

    @Operation(summary = "대회 대상자 등록", description = "")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseData<String>> register(@Validated @RequestBody RegisterTargetRequest request) {
        targetService.register(request);
        return ResponseEntity.ok(ResponseData.ok("새로운 대상이 성공적으로 등록되었습니다."));
    }
    @Operation(summary = "대회 대상자 목록", description = "")
    @GetMapping()
    public ResponseEntity list(){
        List<TargetDTO> result = targetService.list();
        return ResponseEntity.ok(ResponseData.ok(result, "조회 성공"));
    }
}