package com.galendar.global.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galendar.global.exception.CustomException;
import com.galendar.global.exception.response.ErrorResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            setResonse(response, e.getMessage());
        }
    }

    private void setResonse(HttpServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String jsonResponse = new ObjectMapper().writeValueAsString(new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, message));
        response.getWriter().print(jsonResponse);
    }

}
