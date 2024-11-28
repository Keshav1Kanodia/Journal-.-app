//package com.journalblog.JournalApp.service;
//
//import com.journalblog.JournalApp.entity.User;
//import com.journalblog.JournalApp.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.provider.Arguments;
//import org.mockito.*;
//import org.mockito.Mockito.*;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class CustomUserDetailsServiceTest {
//
//    @InjectMocks
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//    @Disabled
//    @Test
//    void loadUserNameByUserName(){
//        Mockito.when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("aassddffvbdd").build());
//        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername("arjun");
//        assertNotNull(userDetails);
//    }
//}
