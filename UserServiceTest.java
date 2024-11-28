//package com.journalblog.JournalApp.service;
//import com.journalblog.JournalApp.entity.User;
//import com.journalblog.JournalApp.repository.UserRepository;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Disabled
//    @Test
//    public void testAdd(){
//        assertEquals(3,2+1);
//    }
//
//
//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings={"arjun","komal"})
//    public void findByUserNameTest(String name){
//        assertNotNull(userRepository.findByUserName(name));
//    }
//
//    @Disabled
//    @Test
//    public void checkJournalEntityOfUser(){
//        User user = userRepository.findByUserName("komal");
//        assertTrue(!user.getList().isEmpty());
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({"1,1,2","2,2,4","4,4,8"})
//    public void test(int a,int b,int expected){
//       assertEquals(expected,a+b);
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentSource.class)
//    public void saveNewUser(User user) throws Exception {
//        assertNotNull(userService.createUser(user));
//    }
//
//}
