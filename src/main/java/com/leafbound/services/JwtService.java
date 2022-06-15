package com.leafbound.services;

import java.io.IOException;
import java.security.InvalidKeyException;

import com.leafbound.models.UserDTO;

public interface JwtService {

    public UserDTO getDTO(String jwt);

    public UserDTO parseJwt(String jwt) throws IOException;

    public String createJWT(UserDTO userDTO) throws InvalidKeyException, io.jsonwebtoken.security.InvalidKeyException;
}
