package com.sheshu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sheshu.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    // Correct method declaration for finding by username
    Optional<User> findByUsername(String username);
    
    
    
//    User saveUser(User user); 
}
