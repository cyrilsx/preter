package ch.collen.preterbackendserver.config;

import ch.collen.preterbackendserver.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private final SecretKey secret;
    private final long expirationTime;

    public JWTUtil(String secret, long expirationTime) {
        this.secret = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationTime = expirationTime;
    }

    public static SecretKey convertStringToSecretKeyto(String encodedKey) {
        SecretKey key = Jwts.SIG.HS512.key().build();

        System.out.printf(key.getEncoded().toString());

        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HS512");
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
