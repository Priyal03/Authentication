package com.pri.ws;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {

	
	private String SECRET_KEY="SECRET";

	public String generateToken(UserDetails user) {
		Map<String, Object> claims=new HashMap<String, Object>();
		return createToken(claims,user.getUsername());
	}

	private String createToken(Map<String, Object> claims, String username) {
		// TODO Auto-generated method stub
		long now =System.currentTimeMillis();
		long later=now+1000*3600;
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(now)).setExpiration(new Date(later))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims,T> object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
		
	}
	
	public boolean validateToken(String token, UserDetails user) {
		String username = extractUsername(token);
		return (!isTokenExpired(token) && username.equals(user.getUsername()));
	}
}

