package com.k.garlander.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class AdminController {
    @GetMapping("/admin")
    public String adminP(){ //jjhkkkljkjkll;;ljkl;lj;lkjkljlkjljjkl;jl;k;lkj
        return "Admin Controller";
    }

}