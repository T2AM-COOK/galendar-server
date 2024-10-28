package com.galendar.domain.contest.controller;

import com.galendar.domain.contest.dto.request.ContestRequest;
import com.galendar.domain.contest.dto.request.RegisterContestRequest;
import com.galendar.domain.contest.dto.response.ContestResponse;
import com.galendar.domain.contest.service.ContestService;
import com.galendar.domain.contest.service.querydsl.ContestQueryService;
import com.galendar.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "대회 등록")
@RestController
@RequestMapping("/contest")
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;
    private final ContestQueryService contestQueryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<ResponseData> register( @Validated @RequestBody RegisterContestRequest request) {
        contestService.register(request);
        return ResponseEntity.ok(ResponseData.ok("대회가 성공적으로 등록되었습니다."));
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseData> list(@RequestBody ContestRequest request) {
        List<ContestResponse> result = contestQueryService.list(request);
        return ResponseEntity.ok(ResponseData.ok(result, "조회 성공"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(ResponseData.ok(contestQueryService.get(id), "조회 성공"));
    }
}