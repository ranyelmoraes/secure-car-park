package com.pitang.securecarpark.securecarpark.repository;

import com.pitang.securecarpark.securecarpark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = ?1")
    boolean findByEmailAddress(String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.login= ?1")
    boolean findByUsername(String login);

    Optional<User> findByLogin(String login);
}
