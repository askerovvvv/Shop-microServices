package com.microservice.security2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE username=:username", nativeQuery = true)
    Optional<User>findByUsername(String username);

    @Modifying
    @Query(value = "DELETE from users WHERE username=:username", nativeQuery = true)
    void deleteByUsername(String username);
}
