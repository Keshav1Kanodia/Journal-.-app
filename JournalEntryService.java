package com.journalblog.JournalApp.service;

import com.journalblog.JournalApp.entity.JournalEntity;
import com.journalblog.JournalApp.entity.User;
import com.journalblog.JournalApp.repository.JournalEntryRepository;
import com.journalblog.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public JournalEntity createJournalEntity(JournalEntity journalEntity,String userName) throws Exception {

        try{
            User existsUser = userRepository.findByUserName(userName);

            journalEntity.setDate(LocalDate.now());
            JournalEntity savedJournalEntity =  journalEntryRepository.save(journalEntity);

            existsUser.getList().add(savedJournalEntity);
            userService.createNewUser(existsUser);

            return journalEntity;
        }catch (Exception e){
            throw new Exception("Error occur while saving new entry journal entry !"+e);
        }
    }

    public void createJournalEntity(JournalEntity journalEntity){
        this.journalEntryRepository.save(journalEntity);
    }

    public Optional<JournalEntity> getById(ObjectId id) throws Exception {
        try{
            return journalEntryRepository.findById(id);
        }catch (Exception e){
            log.error("Error occurred while get one specific journal entity by id "+e.getMessage());
            throw new Exception("User journal entityt not found by id "+e.getMessage());
        }
    }

    public List<JournalEntity>getAllJournalEntity(String userName) throws Exception {
       try{
           User existUser = this.userService.findByName(userName);
           return existUser.getList();
       }catch (Exception e){
           log.error("get all journal entity"+e.getMessage());
           throw new Exception("Error occurred while get all journla entity "+e.getMessage());
       }
    }

    @Transactional
    public boolean deleteJournalEntity(ObjectId id,String username) throws Exception {

        boolean remove=false;
       try{
           log.error("I am enter in delete journal entity service");
           User user  = this.userService.findByName(username);
           remove = user.getList().removeIf(obj->obj.getId().equals(id));

           if(remove){
               log.info("Remove true journal entity delete successfully ");
               userService.createNewUser(user);
               journalEntryRepository.deleteById(id);
           }
       }catch (Exception e){
           log.error("Error Occurred while delete journal entity {}",e.getMessage());
           throw new Exception("Error occurred while delete the journal entity");
       }
        return remove;
    }
}
