package edu.nju.service.impl;

import edu.nju.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.time.LocalDateTime;

class JobServiceImplTest {

    private ApplicationContext context = null;
    private JobService jobService = null;

    @BeforeEach
    void setUp() {

        //如果是读取/WEB-INF/applicationContext.xml
        context = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
        jobService = context.getBean("jobService", JobService.class);
    }

    @Test
    void addCheckUnpaidOrderJob() {
        jobService.addCheckUnpaidOrderJob(LocalDateTime.now(), 1);
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}