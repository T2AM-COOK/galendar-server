package com.k.garlander.domain.user.service;

import com.k.garlander.global.cache.RedisService;
import com.k.garlander.domain.user.dto.req.RefreshTokenRequest;
import com.k.garlander.domain.user.entity.enums.Role;
import com.k.garlander.global.exception.RefreshTokenValidator;
import com.k.garlander.global.security.jwt.JwtUtil;
import com.k.garlander.global.security.jwt.dto.JwtTokenDTO;
import com.k.garlander.global.security.properties.JwtProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RedisService redisService;
    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;
    private final RefreshTokenValidator refreshTokenValidator;

    public JwtTokenDTO reissue(HttpServletRequest request, RefreshTokenRequest tokenRequest) {
        String device = request.getHeader("User-Agent") + "_" + request.getRemoteAddr();
        String refresh = tokenRequest.getRefresh();

        refreshTokenValidator.validate(device, refresh);

        String email = jwtUtil.getEmail(refresh);
        Role role = Role.valueOf(jwtUtil.getRole(refresh));

        String newAccess = jwtUtil.createJwt("access", email, role, device, jwtProperties.getAccess().getExpiration());
        String newRefresh = jwtUtil.createJwt("refresh", email, role, device, jwtProperties.getRefresh().getExpiration());

        redisService.saveToken(device + "::" + email, newRefresh, jwtProperties.getRefresh().getExpiration());

        return JwtTokenDTO.builder().access(newAccess).refresh(newRefresh).build();
    }

    public void logout(HttpServletRequest request) {
        String refresh = request.getHeader("Refresh");
        String device = request.getHeader("User-Agent") + "_" + request.getRemoteAddr();

        refreshTokenValidator.validate(device, refresh);

        deleteTokenByEmail(device, jwtUtil.getEmail(refresh));
    }

    private void deleteTokenByEmail(String device, String email) {
        redisService.deleteOnRedisForToken(device + "::" + email);
    }
}