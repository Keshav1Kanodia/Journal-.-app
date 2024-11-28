//package com.journalblog.JournalApp.service;
//
//import com.journalblog.JournalApp.entity.User;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//
//import java.util.stream.Stream;
//
//public class UserArgumentSource implements ArgumentsProvider {
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
//        return Stream.of(
//                Arguments.of(User.builder().userName("pintu").password("pintu").build()),
//                Arguments.of(User.builder().userName("khushi").password("khushi").build())
//        );
//    }
//}
