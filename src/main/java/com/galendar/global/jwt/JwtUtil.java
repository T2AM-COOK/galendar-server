package com.galendar.global.jwt;

import com.galendar.domain.user.dto.User;
import com.galendar.domain.user.exception.UserNotFoundException;
import com.galendar.domain.user.mapper.UserMapper;
import com.galendar.domain.user.repository.UserRepository;
import com.galendar.global.jwt.config.JwtProperties;
import com.galendar.global.jwt.enums.JwtType;
import com.galendar.global.jwt.exception.TokenTypeException;
import com.galendar.global.security.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Jws<Claims> getClaims(final String token) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new JwtException("Expired JWT");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Unsupported JWT");
        } catch (MalformedJwtException e) {
            throw new JwtException("Invalid JWT");
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT");
        } catch (IllegalArgumentException e) {
            throw new JwtException("JWT claims string is empty");
        }
    }

    public String generateAccessToken(final String email) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                .compact();
    }

    public String generateRefreshToken(final String email) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpiration()))
                .signWith(SignatureAlgorithm.HS256, Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                .compact();
    }

    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = getClaims(token);

        if (isWrongType(claims, JwtType.ACCESS)) {
            throw TokenTypeException.EXCEPTION;
        }

        User user = userRepository.findByEmail(claims.getBody().getSubject()).map(userMapper::toDTO).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        final CustomUserDetails customUserDetails = CustomUserDetails.create(user);

        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        return extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    public boolean isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }

}