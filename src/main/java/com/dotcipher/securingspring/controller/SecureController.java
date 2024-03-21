package com.dotcipher.securingspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/welcome")
    public String sayWelcome(){
        return "Welcome to Spring Application without security";
    }

}
