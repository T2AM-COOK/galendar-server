package com.galendar.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "Admin", description = "")
@Controller
@ResponseBody
public class AdminController {
    @Operation(summary = "권한")
    @GetMapping("/admin")
    public String adminP(){ //jjhkkkljkjkll;;ljkl;lj;lkjkljlkjljjkl;jl;k;lkj
        return "Admin Controller";
    }

}