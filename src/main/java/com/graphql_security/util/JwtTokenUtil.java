package com.graphql_security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Getter
@Component
public class JwtTokenUtil {

    @Value(value = "${spring.security.jwt.secret}")
    private String jwtSecret;

    @Value(value = "${spring.security.jwt.expiration}")
    private long jwtExpiration;

    @Value(value = "${spring.security.jwt.tokenPrefix}")
    private String tokenPrefix;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + getJwtExpiration());
        return getTokenPrefix() + JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC512(getJwtSecret().getBytes()));
    }

    public String getUsernameFromToken(String token) {
        return JWT.decode(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(getJwtSecret().getBytes()))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(getTokenPrefix()))
            return bearerToken.substring(getTokenPrefix().length());
        return null;
    }
} 