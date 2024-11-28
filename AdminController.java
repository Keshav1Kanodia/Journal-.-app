package com.journalblog.JournalApp.controllers;

import com.journalblog.JournalApp.entity.JournalEntity;
import com.journalblog.JournalApp.entity.User;
import com.journalblog.JournalApp.service.JournalEntryService;
import com.journalblog.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/getAll/users")
    public ResponseEntity<List<User>> getAllUser(){
        List<User>userList = this.userService.getAllUser();

        if(userList!=null && !userList.isEmpty()){
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public ResponseEntity<User> createAdmin(@RequestBody User user){
        User admin = this.userService.createAdmin(user);

        if(admin==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(admin,HttpStatus.CREATED);
    }

}
