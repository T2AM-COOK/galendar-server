package com.galendar.domain.auth.service;

import com.galendar.domain.auth.dto.request.AuthenticationRequest;
import com.galendar.domain.auth.dto.request.RefreshTokenRequest;
import com.galendar.domain.auth.dto.request.SignupRequest;
import com.galendar.domain.auth.dto.response.JsonWebTokenResponse;
import com.galendar.domain.user.dto.User;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.domain.user.entity.enums.UserRole;
import com.galendar.domain.user.repository.UserRepository;
import com.galendar.global.jwt.JwtUtil;
import com.galendar.global.jwt.enums.JwtType;
import com.galendar.global.jwt.exception.TokenTypeException;
import com.galendar.global.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public void signup(SignupRequest request) {
        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(UserRole.USER)
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public JsonWebTokenResponse auth(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        return JsonWebTokenResponse.builder()
                .accessToken(
                        jwtUtil.generateAccessToken(user.getEmail())
                )
                .refreshToken(
                        jwtUtil.generateRefreshToken(user.getEmail())
                )
                .build();
    }

    @Override
    public JsonWebTokenResponse refresh(RefreshTokenRequest request) {
        Jws<Claims> claims = jwtUtil.getClaims(jwtUtil.extractToken(request.getRefreshToken()));
        if (jwtUtil.isWrongType(claims, JwtType.REFRESH)) {
            throw TokenTypeException.EXCEPTION;
        }
        return JsonWebTokenResponse.builder()
                .accessToken(jwtUtil.generateAccessToken(claims.getBody().getSubject()))
                .refreshToken(jwtUtil.generateRefreshToken(claims.getBody().getSubject()))
                .build();
    }


}
