package com.galendar.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galendar.global.exception.CustomException;
import com.galendar.global.exception.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String jsonResponse = new ObjectMapper().writeValueAsString(new ErrorResponse(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다."));
        response.getWriter().print(jsonResponse);
    }
}
