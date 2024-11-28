package com.journalblog.JournalApp.service;

import com.journalblog.JournalApp.entity.User;
import com.journalblog.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user) throws Exception {
       try{
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRoles(Arrays.asList("USER"));
           return userRepository.save(user);

       }catch (Exception e) {
           log.info("Duplicates user name present " + e.getMessage());
           throw new Exception("Error occur while saving new user " + e.getMessage());
       }
    }

    public User createNewUser(User user){
        return userRepository.save(user);
    }

    public User createAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        return userRepository.save(user);
    }


    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }
    public User findByName(String userName){
        return this.userRepository.findByUserName(userName);
    }
}
