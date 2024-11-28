package com.journalblog.JournalApp.service;

import com.journalblog.JournalApp.entity.User;
import com.journalblog.JournalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User existUser = this.userRepository.findByUserName(username);

        if(existUser!=null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(existUser.getUserName())
                    .password(existUser.getPassword())
                    .roles(existUser.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User Not Found !");
    }
}
