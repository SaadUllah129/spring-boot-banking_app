package com.modern.banking.app.utils;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	private static String secret;

	@Value("${jwt.secret}")
	public void setSecret(String secret) { // ✅ Constructor injection for final fields
		JwtUtil.secret = secret;
	}

	@Value("${jwt.expiration}")
	private long expirationTime;

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public String generateToken(String email) {
		return Jwts.builder().subject(email).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 1 hour
				.signWith(getSigningKey()) // ✅ Correctly signing with SecretKey
				.compact();
	}
	
	public String generateRefreshToken(String email) {
		return Jwts.builder().subject(email).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 30)) // 1 hour
				.signWith(getSigningKey()) // ✅ Correctly signing with SecretKey
				.compact();
	}
	

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		System.out.println("Received Token: " + token);
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()) // Instead of `setSigningKey()` -> deprecated, use
															// `verifyWith()`
				.build().parseSignedClaims(token).getPayload();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
