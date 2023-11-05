package ch.collen.preterbackendserver.config;

import ch.collen.preterbackendserver.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private final String secret;
    private final String expirationTime;

    public JWTUtil(String secret, String expirationTime) {
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(this.secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
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
        Long expirationTimeLong = Long.parseLong(expirationTime); //in second
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
