package ua.in.kp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HEADER_NAME);
        if (StringUtils.hasText(header) && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(TOKEN_PREFIX.length()).trim();
        }
        return null;
    }

    public boolean validate(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getSubjectFromToken(String token) {
        return Encryptor.decrypt(getClaims(token).getSubject());
    }

    private Date mapToDate(LocalDateTime currentDateTime) {
        return Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
