package com.journalblog.JournalApp.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class journalentityserviceTest {

    @Autowired
    private JournalEntryService journalEntryService;

//    @ParameterizedTest
//    @CsvSource({"66f03af9f1ede008e11a4589"})
//    void getById(ObjectId id) throws Exception {
//        assertNotNull(journalEntryService.getById(id));
//    }
//    @ParameterizedTest
//    @CsvSource({"66f03af9f1ede008e11a4589 raj"})
//    void deleteJournalEntity(ObjectId id,String name) throws Exception {
//        assertNotNull(journalEntryService.deleteJournalEntity(id, name));
//    }
}
