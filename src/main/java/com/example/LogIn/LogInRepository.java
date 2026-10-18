package com.example.LogIn;

import com.example.LogIn.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogInRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    
}
