package com.pitang.securecarpark.securecarpark.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "MZsaksmzijdsSWHSszckx2K23SMZMXFJ";
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS=0;
    public static final long EXPIRE_MINUTES = 10;

    private JwtUtils(){}

    private static SecretKey generateKey () {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes((StandardCharsets.UTF_8)));
    }
    private static Date toExpireDate (Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken (String login) {
        Date issuedAt = new Date();
        Date limit = toExpireDate(issuedAt);
        String token = Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .subject(login)
                .issuedAt(issuedAt)
                .expiration(limit)
                .signWith(generateKey())
                .compact();
        return new JwtToken(token);
    }

    private static Claims getClaimsFromToken (String token) {
        try{
            return Jwts
                    .parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(refactor(token))
                    .getPayload();
        }catch (JwtException ex) {
            log.error(String.format("Token invalido %s", ex.getMessage()));
        }
        return null;
    }
    private static String refactor(String token) {
        if (token.contains(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }
    public static String getLogin(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isValid (String token) {
        try{
            Jwts
                    .parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(refactor(token));

            return true;
        }catch (JwtException ex) {
            log.error(String.format("Token invalido %s", ex.getMessage()));
        }
        return false;
    }

}
