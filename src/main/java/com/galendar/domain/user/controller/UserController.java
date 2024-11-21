package com.galendar.domain.user.controller;

import com.galendar.domain.user.dto.response.UserProfileResponse;
import com.galendar.domain.user.service.UserService;
import com.galendar.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "", description = "")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ResponseData<UserProfileResponse>> me(){
        UserProfileResponse profile = userService.getProfile();
        return ResponseEntity.ok(ResponseData.ok(profile, "조회 성공"));
    }
}
