package com.pri.ws.impl;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pri.ws.jwt.model.Role;
import com.pri.ws.jwt.model.RoleName;
 
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}