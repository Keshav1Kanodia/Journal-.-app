package com.journalblog.JournalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
public class JournalEntity {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDate date;

}
