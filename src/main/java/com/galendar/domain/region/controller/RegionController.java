package com.galendar.domain.region.controller;

import com.galendar.domain.region.dto.RegionDTO;
import com.galendar.domain.region.dto.request.RegisterRegionRequest;
import com.galendar.domain.region.service.RegionService;
import com.galendar.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "region", description = "대회 지역 관련 API (관리자)")
@RestController
@RequestMapping("/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;
    @Operation(summary = "대회 지역 등록", description = "")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseData<String>> register(@Validated @RequestBody RegisterRegionRequest request) {
        regionService.register(request);
        return ResponseEntity.ok(ResponseData.ok("새로운 지역을 성공적으로 등록되었습니다."));
    }
    @Operation(summary = "대회 지역 목록", description = "")
    @GetMapping()
    public ResponseEntity list(){
        List<RegionDTO> result = regionService.list();
        return ResponseEntity.ok(ResponseData.ok(result, "조회 성공"));
    }


}
