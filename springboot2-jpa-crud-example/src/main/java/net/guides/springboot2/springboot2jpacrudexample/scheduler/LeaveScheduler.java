package net.guides.springboot2.springboot2jpacrudexample.scheduler;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.guides.springboot2.springboot2jpacrudexample.controller.LeaveController;
import net.guides.springboot2.springboot2jpacrudexample.repository.LeaveRepository;

@Component
public class LeaveScheduler {

    private static final Logger log = LoggerFactory.getLogger(LeaveScheduler.class);
    private static long count = 0;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveController tc;

    @Scheduled(fixedRate = 15000)
    public void createTasks() {

        count++;

       // Task t = new Task();
      //  t.setTitle("Scheduled Task " + count);
     //   tc.create(t);
     //   leaveRepository.saveAndFlush(t);

        log.info("A new scheduled task has been created!");
    }
}