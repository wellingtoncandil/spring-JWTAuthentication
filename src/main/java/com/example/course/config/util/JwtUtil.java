package com.example.course.config.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.course.config.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	private String jwtSecret = "secretOnly11223344";
	
	private int jwtExpirationMs = 36000000;
	
	
	public String generateJwtToken(Authentication authentication) {

	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	    
	    return Jwts.builder()
	        .setSubject((userPrincipal.getEmail()))
	        .setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	        .signWith(SignatureAlgorithm.HS256, jwtSecret)
	        .compact();
	   
	  }

	  public String getUserNameFromJwtToken(String token) {
	    return Jwts.parser()
	    		.setSigningKey(jwtSecret)
	    		.parseClaimsJws(token)
	    		.getBody()
	    		.getSubject();
	  }

	  public boolean validateJwtToken(String authToken) {
		  System.out.println(authToken);
	    try {
	      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	      return true;
	    } catch (SignatureException e) {
	      logger.error("Invalid JWT signature: {}", e.getMessage());
	    } catch (MalformedJwtException e) {
	      logger.error("Invalid JWT token: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
	      logger.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	      logger.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	      logger.error("JWT claims string is empty: {}", e.getMessage());
	    }

	    return false;
	  }
	}
