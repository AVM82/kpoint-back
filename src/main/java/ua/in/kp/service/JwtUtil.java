package ua.in.kp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_NAME = "Authorization";
    private final SecretKey jwtKey;
    @Value("${jwt.token.expiration.time}")
    private Long expirationTime;

    public JwtUtil(@Value("${jwt.token.secret}") String secret) {
        this.jwtKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String subject) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return Jwts.builder()
                .subject(Encryptor.encrypt(subject))
                .issuedAt(mapToDate(currentDateTime))
                .expiration(mapToDate(currentDateTime.plusMinutes(expirationTime)))
                .signWith(jwtKey)
                .compact();
    }

    public boolean validate(String token) {
        return false;
    }

    private Date mapToDate(LocalDateTime currentDateTime) {
        return Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
