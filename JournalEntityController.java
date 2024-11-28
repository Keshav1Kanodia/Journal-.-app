package com.journalblog.JournalApp.controllers;
import com.journalblog.JournalApp.entity.JournalEntity;
import com.journalblog.JournalApp.entity.User;
import com.journalblog.JournalApp.service.JournalEntryService;
import com.journalblog.JournalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/journal")
@RestController
public class JournalEntityController {

    //automatically inject dependency of one class into the another class.
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<JournalEntity>> getAllUserJournalEntry(){

       try{
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String userName = authentication.getName();

           List<JournalEntity>all = journalEntryService.getAllJournalEntity(userName);

           if(all==null){
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<>(all, HttpStatus.OK);
       }catch (Exception e){
           log.error("Error occurred while get all journal entity controller"+e.getMessage());
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<JournalEntity> getById(@PathVariable ObjectId id){

       try{
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String userName = authentication.getName();
           User user = this.userService.findByName(userName);

           List<JournalEntity> collect = user.getList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

           if(!collect.isEmpty()){
               Optional<JournalEntity>journalEntity =  journalEntryService.getById(id);

               if(journalEntity.isPresent()){
                   return new ResponseEntity<>(journalEntity.get(),HttpStatus.OK);
               }
               return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
           }
       }catch (Exception e){
           log.error("Error occurred while get one single journal entity "+e.getMessage());
       }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }


    @PostMapping("/create")
    public ResponseEntity<JournalEntity> createEntity(@RequestBody JournalEntity journalEntity){

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            JournalEntity savedJournalEntity = journalEntryService.createJournalEntity(journalEntity,userName);

            return new ResponseEntity<>(savedJournalEntity,HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Error occurred while create a new journal entity "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJournalEntity(@PathVariable ObjectId id,@PathVariable String username) {

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();


            boolean remove = journalEntryService.deleteJournalEntity(id,username);

            if(remove){
                return new ResponseEntity<>("Delete Successfully ",HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.error("Error occurred while delete a journal entity "+e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntity> updateJournalEntity(@PathVariable ObjectId id,@RequestBody JournalEntity newJournalEntity){

       try{
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String userName = authentication.getName();

           User user = this.userService.findByName(userName);

           List<JournalEntity> collect = user.getList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

           if(!collect.isEmpty()){
               Optional<JournalEntity>journalEntity =  journalEntryService.getById(id);

               if(journalEntity.isPresent()){
                   JournalEntity existsJournalEntity = journalEntity.get();

                   existsJournalEntity.setContent(newJournalEntity.getContent()!=null && !newJournalEntity.getContent().equals("")? newJournalEntity.getContent() : existsJournalEntity.getContent());
                   existsJournalEntity.setTitle(newJournalEntity.getTitle()!=null && !newJournalEntity.getTitle().equals("")? newJournalEntity.getTitle() : existsJournalEntity.getTitle());

                   journalEntryService.createJournalEntity(existsJournalEntity);

                   return new ResponseEntity<>(existsJournalEntity,HttpStatus.OK);
               }
           }
       }catch (Exception e){
           log.error("Error occurred while update journal entity "+e.getMessage());
       }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
