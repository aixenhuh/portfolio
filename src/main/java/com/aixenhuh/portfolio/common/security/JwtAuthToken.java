package com.aixenhuh.portfolio.common.security;

import com.aixenhuh.portfolio.exception.CustomJwtRuntimeException;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class JwtAuthToken implements AuthToken<Claims> {
    @Getter
    private final String token;
    private final Key key;

    private static final String AUTHORITIES_KEY = "role";

    JwtAuthToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    JwtAuthToken(String id, String role, Date expiredDate, Key key) {
        this.key = key;
        this.token = createJwtAuthToken(id, role, expiredDate).get();
    }

    @Override
    public boolean validate() {
        return getData() != null;
    }

    @Override
    public Claims getData() {

        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (SecurityException e) {
            log.info("Invalid JWT signature.");
            throw new CustomJwtRuntimeException();
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            throw new CustomJwtRuntimeException();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            throw new CustomJwtRuntimeException();
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            throw new CustomJwtRuntimeException();
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            throw new CustomJwtRuntimeException();
        }
    }

    private Optional<String> createJwtAuthToken(String id, String role, Date expiredDate) {

        var token = Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITIES_KEY, role)
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(expiredDate)
                .compact();

        return Optional.ofNullable(token);
    }

}
