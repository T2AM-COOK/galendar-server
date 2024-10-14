package com.galendar.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galendar.global.exception.CustomException;
import com.galendar.global.exception.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String jsonResponse = new ObjectMapper().writeValueAsString(new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()));
        response.getWriter().print(jsonResponse);
    }
}
