package br.com.alura.forum.security.jwt;

import br.com.alura.forum.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {

    @Value("${alura.forum.jwt.secret}")
    private String secret;

    @Value("${alura.forum.jwt.expiration}")
    private long expirationInMillis;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationInMillis);

        return Jwts.builder()
                .setIssuer("Alura Fórum API")
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean isValid(String jwt) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
