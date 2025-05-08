package com.joaquin.ClinicaMVC.configuration;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    //Secret Key. This should be secret not uploaded
    private static final String SECRET_KEY = "0fe312894ecbd9315e85c5b1be2602237100b027292d7b5aaf7ffd70db0924f0";

    //this function uses the extractClaim function to retrieve the username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // generic function to retrieve any claim
    public<T> T extractClaim(String token, Function<Claims, T> claimsFunction){
        Claims claims = extractAllClaims(token);
        
        return claimsFunction.apply(claims);
    }

    // uses the Jwts class to extract all the claims of the token
    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()//parse the token
                .setSigningKey(getSinginKey()) //use the secret key to set the singing key
                .build() //build the Claims map
                .parseClaimsJws(token) // parse the claims
                .getBody(); // get all the claims
    }
    // decode and aply the algorithm to the secret key
    private Key getSinginKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //using the Jwts class build a token seting the claims, username and expiration date
    public String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24)) // 24 hours after current date
                .signWith(getSinginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    // verifies if the token is valid using the username and the expiration date
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUsername(token);

        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //get the expiration date and compares it to the current date
    private boolean isTokenExpired(String token) {
        final Date expirationDate = extractClaim(token, Claims::getExpiration);

        return new Date(System.currentTimeMillis()).after(expirationDate);
    }

}
