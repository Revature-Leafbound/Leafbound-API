package com.leafbound.services;

import java.io.IOException;
import java.security.InvalidKeyException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leafbound.models.User;
import com.leafbound.models.UserDTO;

public interface JwtService {
    public String createJwt(User user) throws InvalidKeyException, JsonProcessingException;

    public UserDTO getDTO(String jwt);

    public UserDTO parseJwt(String jwt) throws IOException;
}
