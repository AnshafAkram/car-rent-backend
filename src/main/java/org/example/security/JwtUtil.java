package org.example.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;


@Component
public class JwtUtil {


    private final String secret =
            "mySecretKeyForCarRentalSystem2026VeryLongKey";


    private final long expiration = 86400000;



    private SecretKey getKey(){

        return Keys.hmacShaKeyFor(
                secret.getBytes()
        );

    }



    public String generateToken(String email){


        return Jwts.builder()

                .subject(email)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration
                        )
                )

                .signWith(getKey())

                .compact();

    }



    public String extractEmail(String token){

        return Jwts.parser()

                .verifyWith(getKey())

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .getSubject();

    }



    public boolean validateToken(String token){

        try{

            Jwts.parser()

                    .verifyWith(getKey())

                    .build()

                    .parseSignedClaims(token);


            return true;

        }
        catch(Exception e){

            return false;

        }

    }

    public boolean validateToken(String token, UserDetails userDetails) {

        String email = extractEmail(token);

        return email.equals(userDetails.getUsername()) &&
                validateToken(token);
    }

}