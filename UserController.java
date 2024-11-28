package com.journalblog.JournalApp.controllers;
import com.journalblog.JournalApp.entity.User;
import com.journalblog.JournalApp.repository.UserRepository;
import com.journalblog.JournalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping
    public ResponseEntity<String> deleteUser(){

       try{
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String userName = authentication.getName();

           this.userRepository.deleteByUserName(userName);
           return new ResponseEntity<>("Delete Successfully ",HttpStatus.NO_CONTENT);
       }catch (Exception e){
           log.error("Error occurred while delete user "+e.getMessage());
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            User existsUser = userService.findByName(userName);

            if(existsUser!=null){
                existsUser.setUserName(user.getUserName());
                existsUser.setPassword(user.getPassword());
                this.userService.createUser(existsUser);

                return new ResponseEntity<>(existsUser,HttpStatus.OK);
            }
        }catch (Exception e){
            log.error("Error occurred while update a user controller "+e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
