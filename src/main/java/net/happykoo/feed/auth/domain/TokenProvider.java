package net.happykoo.feed.auth.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenProvider {
    private final SecretKey key;
    private static final long TOKEN_VALID_TIME = 1000L * 60 * 60;

    public TokenProvider(@Value("${secret-key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Long userId, String role) {
        Claims claims = Jwts.claims()
                .subject(userId.toString())
                .build();

        Date now = new Date();
        Date validateDate = new Date(now.getTime() + TOKEN_VALID_TIME);
        return Jwts.builder()
                .claims(claims)
                .claim("role", role)
                .expiration(validateDate)
                .issuedAt(now)
                .signWith(key)
                .compact();
    }

    public Long getUserId(String token) {
        return Long.parseLong(Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());
    }

    public String getUserRole(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }
}
