package com.anywr.oki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anywr.oki.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByUserNameAndPassword(String userName, String password);
    public User findByUserName(String username);
}