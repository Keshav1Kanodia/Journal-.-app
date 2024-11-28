package com.journalblog.JournalApp.controllers;

import com.journalblog.JournalApp.entity.User;
import com.journalblog.JournalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class HealthChecker {


    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthChecker(){
        return "Ok";
    }
    @PostMapping("/signup")
    public ResponseEntity<User> createEntity(@RequestBody User user) {

        try {
            User savedUser = userService.createUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while signup a user"+e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
