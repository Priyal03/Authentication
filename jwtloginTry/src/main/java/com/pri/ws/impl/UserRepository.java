package com.pri.ws.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pri.ws.jwt.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
