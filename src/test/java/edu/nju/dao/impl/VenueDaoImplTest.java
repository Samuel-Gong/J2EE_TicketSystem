package edu.nju.dao.impl;

import edu.nju.dao.VenueDao;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenueSeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

class VenueDaoImplTest {

    private static ApplicationContext context = null;
    private static VenueDao venueDao = null;

    @BeforeAll
    static void setUpBeforeAll() {
        //如果是读取/WEB-INF/applicationContext.xml
        context = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/dao.xml");
        venueDao = context.getBean("venueDao", VenueDaoImpl.class);
    }

    @Test
    void getVenue() {
        Venue venue = venueDao.getVenue(2);
        System.out.println(venue.getSeatMap().size());
    }

    @Disabled
    @Test
    void addVenue() {

        Venue venue = new Venue();
        venue.setColumnNum(10);
        venue.setRowNum(10);
        venue.setName("南京大剧院");
        venue.setCity("南京");
        venue.setPassword("123456");

        VenueSeat venueSeat = new VenueSeat();
        venueSeat.setColumn(10);
        venueSeat.setRow(10);

        venueSeat.setHasSeat(true);

        venue.getSeatMap().add(venueSeat);

        Assertions.assertEquals(true, venueDao.addVenue(venue));

    }

    @Disabled
    @Test
    void updateSeatMap() {

        VenueSeatInfoDTO venueSeatInfoDTO = new VenueSeatInfoDTO();

        venueSeatInfoDTO.setRowNum(10);
        venueSeatInfoDTO.setColumnNum(10);
        venueSeatInfoDTO.setVenueId(2);

        List<VenueSeat> venueSeats = new ArrayList<>();

        VenueSeat venueSeat = new VenueSeat();
        venueSeat.setColumn(9);
        venueSeat.setRow(9);

        venueSeat.setHasSeat(false);

        venueSeats.add(venueSeat);

        Assertions.assertEquals(true, venueDao.updateSeatMap(venueSeatInfoDTO));

    }

    @Test
    void getComingVenuePlansTotalNum() {

        Assertions.assertEquals(0, venueDao.getComingVenuePlanTotalNum());

    }

}