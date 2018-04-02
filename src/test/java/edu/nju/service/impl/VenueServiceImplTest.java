package edu.nju.service.impl;

import edu.nju.dto.VenuePlanDetailDTO;
import edu.nju.service.VenueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

class VenueServiceImplTest {

    private ApplicationContext context = null;
    private VenueService venueService = null;

    @BeforeEach
    void setUp() {

        //如果是读取/WEB-INF/applicationContext.xml
        context = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
        venueService = context.getBean("venueService", VenueService.class);
    }

    @Test
    void getVenuePlanOrders() {
        Assertions.assertEquals(1, venueService.getVenuePlanOrders(1).size());
    }

}