package com.ownsprojects.ecomerce.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * Represents the configuration of a jwt provider.
 */
@Slf4j
@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    /**
     * Generate an access token.
     * @param username The username to generate an access token.
     * @return The access token.
     */
    public String generateAccessToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validate the access token.
     * @param token The token to validate.
     * @return true if the token is valid.
     */
    public boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch (Exception e){
            log.error("Invalid token, error: ".concat(e.getMessage()));
            return false;
        }
    }

    /**
     * Get the username associated with the given token.
     * @param token The token to retrieve.
     * @return The username associated with the given token.
     */
    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Get a unique claim.
     * @param token The token to retrieve.
     * @param claimsTFunction The function to call to retrieve.
     * @return The unique claim.
     * @param <T> The type of the returned value.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    /**
     * Get all the claims for a given token.
     * @param token The token of the claim.
     * @return All the claims for the given token.
     */
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Get the signing of the token.
     * @return The signing key.
     */
    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
