package com.example.LogIn;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LogInController {
    private final LogInService logInService;
    LogInController(LogInService logInService){
        this.logInService = logInService;
    }
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password
            ){
        return logInService.registerUser(username,password);
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password
    ){
        return logInService.logInUser(username,password);
    }



}
