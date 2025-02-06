package com.galendar.domain.profile.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Hidden
@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final Environment environment;

    @GetMapping("/profile")
    public String profile(){
        List<String> profiles = Arrays.stream(environment.getActiveProfiles()).toList();
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);
        return profiles.stream().filter(realProfiles::contains).findAny().orElse(defaultProfile);
    }

}
