package com.garlander.domain.auth.controller;

import com.garlander.domain.auth.dto.JoinDto;
import com.garlander.domain.auth.service.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "Join", description = "")
@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }
    @Operation(summary = "가입")
    @PostMapping("/join")
    public String joinProcess(JoinDto joinDto){

        joinService.joinProcess(joinDto);

        return "ok";
    }
}