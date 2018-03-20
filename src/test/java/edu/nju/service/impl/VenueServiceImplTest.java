package edu.nju.service.impl;

import com.alibaba.fastjson.JSON;
import edu.nju.model.SeatType;
import edu.nju.model.VenuePlan;
import edu.nju.model.VenuePlanSeat;
import edu.nju.service.VenueService;
import edu.nju.util.ShowType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    void addVenuePlan() {

        VenuePlan venuePlan = new VenuePlan();

        List<SeatType> seatTypeList = new ArrayList<>();
        SeatType seatType = new SeatType();
        seatType.setTypeChar('b');
        seatType.setPrice(100);
        seatType.setDescription("vip");

        List<VenuePlanSeat> venuePlanSeats = new ArrayList<>();
        VenuePlanSeat venuePlanSeat = new VenuePlanSeat();
        venuePlanSeat.setTypeChar('b');
        venuePlanSeat.setRow(10);
        venuePlanSeat.setColumn(10);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        venuePlan.setBegin(LocalDateTime.parse("2016-10-10 10:10", dateTimeFormatter));
        venuePlan.setEnd(LocalDateTime.parse("2016-10-10 10:12", dateTimeFormatter));

        venuePlan.setSeatTypes(seatTypeList);
        venuePlan.setVenuePlanSeats(venuePlanSeats);
        venuePlan.setShowType(ShowType.CONCERT);
        venuePlan.setDescription("演唱会");

        venueService.addVenuePlan(1, venuePlan);

    }

    @Test
    void getComingVenuePlans(){
//        Assertions.assertEquals(1, venueService.getComingVenuePlan().size());
        System.out.println(JSON.toJSONString(venueService.getComingVenueBriefPlan()));
    }
}