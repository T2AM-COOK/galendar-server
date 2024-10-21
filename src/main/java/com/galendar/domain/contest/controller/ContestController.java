package com.galendar.domain.contest.controller;

import com.galendar.domain.contest.dto.ContestDto;
import com.galendar.domain.contest.res.BaseResponse;
import com.galendar.domain.contest.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contest")
@RequiredArgsConstructor
public class ContestController {
    private final ContestService contestService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerContest(@RequestBody ContestDto contestDto) {
        contestService.registerContest(contestDto);
        return ResponseEntity.ok(new BaseResponse("대회가 성공적으로 등록되었습니다."));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ContestDto>> getContestList() {
        List<ContestDto> contestList = contestService.getAllContests();
        return ResponseEntity.ok(contestList);
    }
}