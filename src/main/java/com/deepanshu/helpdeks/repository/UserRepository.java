package com.deepanshu.helpdeks.repository;

import com.deepanshu.helpdeks.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName( String userName);

    // Add this line so UserService can find the user by username during login
    Optional<User> findByUserName(String userName);
}
