package com.example.User.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String secretKey = "authJNJDCNDJDHJBCHJDBHBDSCHJBDCHJBDCBHDSHJCBDSCB";
    public static String token;
    private final String AUTH_KEY = "Auth-token";

    private final long JWT_TOKEN_VALIDITY = 60 * 60 * 10;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        return Jwts.builder().setClaims(new HashMap<>()).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * JWT_TOKEN_VALIDITY)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    public String extractToken(HttpServletRequest request){
        String authHeader = request.getHeader(AUTH_KEY);
        String token = null;

        // Check token in header
        if (authHeader != null) {
            token = authHeader;
        }

        // Check token in cookies
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(AUTH_KEY)) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }

        return token;
    }
}
