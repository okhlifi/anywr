package com.anywr.oki.config;

import java.util.Map;

import com.anywr.oki.model.User;

public interface JwtGeneratorInterface {

    Map<String, String> generateToken(User user);    
    String getUserNameFromToken(String token);
}
