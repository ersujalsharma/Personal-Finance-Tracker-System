package com.example.finance_tracker_system.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
		@Value("${jwt.secret.key}")
	 	private String SECRET_KEY;
	 	
	    private SecretKey getSigningKey() {
	        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	    }

	    public String extractUsername(String token) {
	        Claims claims = extractAllClaims(token);
	        return claims.getSubject();
	    }

	    public Date extractExpiration(String token) {
	        return extractAllClaims(token).getExpiration();
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(getSigningKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public String generateToken(String username) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, username);
	    }

	    private String createToken(Map<String, Object> claims, String subject) {
	        return Jwts.builder()
	                .claims(claims)
	                .subject(subject)
	                .header().empty().add("typ","JWT")
	                .and()
	                .issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 50)) // 5 minutes expiration time
	                .signWith(getSigningKey())
	                .compact();
	    }

	    public Boolean validateToken(String token) {
	        return !isTokenExpired(token);
	    }
}
