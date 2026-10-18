package com.example.LogIn;

import com.example.LogIn.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogInService {
//    private static final Logger log = LoggerFactory.getLogger(LogInService.class);

    private final LogInRepository logInRepository;
    private final PasswordEncoder passwordEncoder;

    public LogInService(LogInRepository logInRepository, PasswordEncoder passwordEncoder){
        this.logInRepository = logInRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username){
        return logInRepository.findByUsername(username);
    }

    public String registerUser(String username, String password){
        if(username == null ||username.isBlank() ||password== null || password.isBlank()){
            return "use the proper formate";
        }

        Optional<User> existuser = findByUsername(username);
        if(existuser.isPresent()){
            return "already existing user ";
        }
        User user = new User();
        user.setUsername(username);

        String hashpassword = passwordEncoder.encode(password);
        user.setPassword(hashpassword);
        logInRepository.save(user);
        return "You successful register";
    }

    public String logInUser(String username, String password){
        if(username == null ||username.isBlank() ||password== null || password.isBlank()){
            return "use the proper formate";
        }

        Optional<User> existuser = findByUsername(username);
        if(existuser.isEmpty()){
            // check for the password
            return "Please register yourself";
        }

        User user = existuser.get();
        if(passwordEncoder.matches(password, user.getPassword())){
            return "welcome back user";
        }
        else {
            return "Invalid username or password";
        }

    }
}
