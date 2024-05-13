package com.example.User.Schedulers;

import com.example.User.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserScheduler {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public void userDeactivationJob(){
        SimpleDateFormat df =new SimpleDateFormat("dd/MM/yyyy");
        String today = df.format(new Date());
        int updatedCount = userRepository.updateStatusForDeactivatedUsers(today);
        System.out.println(updatedCount + " users were deactivated.");
    }
}
