package com.galendar.global.jwt.filter;

import com.galendar.global.jwt.JwtUtil;
import com.galendar.global.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.extractTokenFromRequest(request);
        if (token != null) {
            SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(token));
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(HttpServletRequest request, HttpServletResponse response, String token) {
        Authentication authentication = createAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication createAuthentication(String token) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
