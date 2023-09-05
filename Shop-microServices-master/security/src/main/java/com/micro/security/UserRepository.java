package com.micro.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM security WHERE username=:username", nativeQuery = true)
    Optional<User> findByUsername(String username);
}
