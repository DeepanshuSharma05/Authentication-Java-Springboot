package com.deepanshu.helpdeks.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {


    private final SecretKey key = Jwts.SIG.HS256.key().build();
    private final long expirationMs = 86400000;


    //Generate Token

    public String generateToken(String userName){
        return Jwts.builder()
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date( System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    //extract username from token;

    public String extractUserName(String token){
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }


    // validate username

    public boolean validateToken(String token , String userName){
        String extractedUser = extractUserName(token);
        return (extractedUser.equals(userName) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        Date expiration = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        return expiration.before(new Date());

    }

}
