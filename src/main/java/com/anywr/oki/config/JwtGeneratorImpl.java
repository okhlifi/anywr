package com.anywr.oki.config;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anywr.oki.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${app.jwttoken.message}")
	private String message;

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserName()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, secret).compact();
		Map<String, String> jwtTokenGen = new HashMap<>();
		jwtTokenGen.put("token", jwtToken);
		jwtTokenGen.put("message", message);

		return jwtTokenGen;
	}

	@Override
	public String getUserNameFromToken(String token) {
		String cleanToken = token.substring(7);
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(cleanToken).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}