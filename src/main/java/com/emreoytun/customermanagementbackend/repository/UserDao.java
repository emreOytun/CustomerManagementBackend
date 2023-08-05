package com.emreoytun.customermanagementbackend.repository;

import com.emreoytun.customermanagementbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Integer> {

    // SpEL
    @Query("SELECT u FROM User u where u.username = :#{#username}")
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
