package edu.nju.model;

import edu.nju.model.embeddable.VenueSeatId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

class VenueTest {

    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @Test
    void addSeat() {
        Session session = sessionFactory.getCurrentSession();

        Transaction tx = session.beginTransaction();

        Venue venue = new Venue();

        VenueSeat seat1 = new VenueSeat();
        VenueSeatId venueSeatId = new VenueSeatId();
        venueSeatId.setRow(10);
        venueSeatId.setColumn(10);

        seat1.setVenueSeatId(venueSeatId);

        seat1.setVenue(venue);
        venue.getSeatMap().add(seat1);

        session.save(venue);

        tx.commit();
    }
}