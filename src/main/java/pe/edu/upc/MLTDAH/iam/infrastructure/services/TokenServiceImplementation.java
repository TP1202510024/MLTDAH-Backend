package pe.edu.upc.MLTDAH.iam.infrastructure.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.edu.upc.MLTDAH.iam.application.internal.outboundservices.TokenService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;


@Service
public class TokenServiceImplementation implements TokenService {
    @Value("${authorization.jwt.secret}")
    private String secret;
    @Value("${authorization.jwt.expiration_days}")
    private int expirationDays;

    @Override
    public String generateToken(String email) {
        return buildTokenWithDefaultParameters(email);
    }

    @Override
    public String getEmailFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    private SecretKey getSignInKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String buildTokenWithDefaultParameters(String email) {
        Date issuedAt = new Date();
        Date expiration = DateUtils.addDays(issuedAt, expirationDays);

        return Jwts.builder().setSubject(email).setIssuedAt(issuedAt).setExpiration(expiration).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

}
