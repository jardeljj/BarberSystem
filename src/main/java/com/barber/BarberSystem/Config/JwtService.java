package com.barber.BarberSystem.Config;

import com.barber.BarberSystem.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    // Generate a token JWT with a user
    public String generateToken(User user){
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 10))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // extract a e-mail of user with a token
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    // extract all claims (informations) of token
    public Claims extractClaims(String token){
       return Jwts.parserBuilder()
               .setSigningKey(getSignKey())
               .build()
               .parseClaimsJwt(token)
               .getBody();
    }

    // Create a key of signature based in secret key
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
