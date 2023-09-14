 package com.nissan.util;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nissan.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	// secret key for token
	private static String secretAdministrator = "THIS_IS_Admin";
	private static String secretCustomer = "THIS_IS_Customer";

	// expiration time
	private static long expiruDuration = 60 * 60;

	// generate token:header.payload.signature

	public String generateJwtAdmin(Users user) {
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiruDuration * 1000;

		// set issuedTime and ExpiryTime in Token
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		// claims
		Claims claims = Jwts.claims().setIssuer(user.getUserId().toString()).setIssuedAt(issuedAt)
				.setExpiration(expiryAt);

		// generate JWT token using claims
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretAdministrator).compact();
	}
	
	public String generateJwtCustomer(Users user) {
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiruDuration * 1000;

		// set issuedTime and ExpiryTime in Token
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		// claims
		Claims claims = Jwts.claims().setIssuer(user.getUserId().toString()).setIssuedAt(issuedAt)
				.setExpiration(expiryAt);

		// generate JWT token using claims
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretCustomer).compact();
	}

	// checking token is valid or not
	public Claims verifyAdmin(String authorization) throws AccessDeniedException {
		try {
			Claims claims = Jwts.parser().setSigningKey(secretAdministrator).parseClaimsJws(authorization).getBody();
			return claims;
		}catch(Exception e) {
			throw new AccessDeniedException("Sorry Access Denied!!");
		}
	}
	
	public Claims verifyCustomer(String authorization) throws AccessDeniedException {
		try {
			Claims claims = Jwts.parser().setSigningKey(secretCustomer).parseClaimsJws(authorization).getBody();
			return claims;
		}catch(Exception e) {
			throw new AccessDeniedException("Sorry Access Denied!!");
		}
	}
}
