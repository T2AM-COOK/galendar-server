package com.galendar.global.jwt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtProperties {

    private String secret;

    private long expiration;

    private long refreshExpiration;

}
