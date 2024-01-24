package com.Pavement.pavement.services.impl;

import java.security.Key;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.Pavement.pavement.services.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService{

	  @Value("${application.security.jwt.secret-key}")
	  private String secretKey;
	  @Value("${application.security.jwt.expiration}")
	  private long jwtExpiration;
	  @Value("${application.security.jwt.refresh-token.expiration}")
	  private long refreshExpiration;
	
	  public String generateToken(UserDetails userDetails) {
		  return Jwts.builder().setSubject(userDetails.getUsername())
				  .setIssuedAt(new Date(System.currentTimeMillis()))
				  .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
				  .signWith(getSignKey(),SignatureAlgorithm.HS256)
				  .compact();		  
	  }
	  
	  public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		  return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
				  .setIssuedAt(new Date(System.currentTimeMillis()))
				  .setExpiration(new Date(System.currentTimeMillis()+refreshExpiration))
				  .signWith(getSignKey(),SignatureAlgorithm.HS256)
				  .compact();		  
	  }
	  
	  private <T> T extractClaim(String token, Function<Claims, T> ClaimsResolver){
		  final Claims claims = extractAllClaims(token);
		  return ClaimsResolver.apply(claims);
	  }
	  
	  private Key getSignKey() {
		byte [] key = Decoders.BASE64.decode(secretKey); 
		return Keys.hmacShaKeyFor(key);
	  }
	  
	  public String extractUserName(String token) {
		  return extractClaim(token,Claims::getSubject);
	  }
	  
	  private Claims extractAllClaims(String token) {
		   return Jwts.parserBuilder()
				   .setSigningKey(getSignKey())
				   .build()
				   .parseClaimsJws(token)
				   .getBody();
	  }
	  
	  public boolean isTokenValid(String token, UserDetails userDetails) {
		  final String userName= extractUserName(token);
		  return(userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	  }
	  
	  private boolean isTokenExpired (String token) {
		  return extractClaim(token, Claims::getExpiration).before(new Date());
	  }

}
