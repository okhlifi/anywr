package com.anywr.oki.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anywr.oki.exception.UserNotFoundException;
import com.anywr.oki.model.User;
 

@Service
public interface UserService {
    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;
    public List<User> findAll();
    public User getProfil(String name);
}
