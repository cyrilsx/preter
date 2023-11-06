package ch.collen.preterbackendserver.config;

import ch.collen.preterbackendserver.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private final SecretKey secret;
    private final long expirationTime;

    public JWTUtil(String secret, long expirationTime) {
        this.secret = Jwts.SIG.HS512.key().build();
        this.expirationTime = expirationTime;
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(this.secret).build().parseSignedClaims(token).getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.roles());
        return doGenerateToken(claims, user.email());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTime * 1000);

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(createdDate)
                .expiration(expirationDate)
                .signWith(this.secret)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
