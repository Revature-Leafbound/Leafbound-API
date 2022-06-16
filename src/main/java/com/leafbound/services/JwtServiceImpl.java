
package com.leafbound.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafbound.models.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

    private Logger logger = Logger.getLogger(JwtServiceImpl.class);

    private Key key;

    public JwtServiceImpl() {
        byte[] secret = System.getenv("Leafbound_JWT_Secret").getBytes();
        key = Keys.hmacShaKeyFor(secret);
    }

    @Override
    public String createJWT(UserDTO userDTO)
            throws InvalidKeyException, io.jsonwebtoken.security.InvalidKeyException {
        logger.info("Within the JwtServiceImpl.createJwt mwthod.");
        logger.info(userDTO.toString());

        // Create the JWT from the UserDTO
        try {
            return Jwts.builder()
                    .claim("user_dto", new ObjectMapper().writeValueAsString(userDTO))
                    .signWith(key)
                    .compact();
        } catch (JsonProcessingException e) {
            throw new InvalidKeyException("JsonProcessingException: " + e.getMessage());
        }
    }

    @Override
    public UserDTO getDTO(String jwt) {

        // Create emty UserDTO object
        UserDTO userDTO = new UserDTO();

        try {
            userDTO = this.parseJwt(jwt);
        } catch (IOException e) {
            logger.warn("Unable to parse JWT: " + e.getMessage());
            return userDTO;
        }

        return userDTO;
    }

    @Override
    public UserDTO parseJwt(String jwt) throws IOException {

        // 1. Generate the token using claims from the jwt and our secret key
        Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

        // 2. Parse the token into a string
        String dtoString = (String) token.getBody().get("user_dto");

        // 3. Send the data to the client as json
        return (new ObjectMapper()).readValue(dtoString, UserDTO.class);
    }
}
