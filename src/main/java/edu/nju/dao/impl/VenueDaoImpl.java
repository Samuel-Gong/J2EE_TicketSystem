package edu.nju.dao.impl;

import edu.nju.dao.VenueDao;
import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.model.VenueSeat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Shenmiu
 * @date 2018/03/16
 * <p>
 * 场馆数据访问接口实现类
 */
@Repository("venueDao")
public class VenueDaoImpl implements VenueDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Venue

    @Override
    public boolean addVenue(Venue venue) {
        sessionFactory.getCurrentSession().save(venue);
        return true;
    }

    @Override
    public Venue getVenue(int venueId) {
        return sessionFactory.getCurrentSession().get(Venue.class, venueId);
    }

    @Override
    public void updateVenue(Venue venue) {
        sessionFactory.getCurrentSession().update(venue);
    }

    @Override
    public String getPassword(int venueId) {
        Session session = sessionFactory.getCurrentSession();
        Query<String> query = session.createQuery("select password from Venue where id = :id", String.class)
                .setParameter("id", venueId);

        return query.getSingleResult();
    }

    @Override
    public boolean updateBasicInfo(VenueBasicInfoDTO venueBasicInfo) {
        Session session = sessionFactory.getCurrentSession();
        int count = session.createQuery("update Venue set name = :name, city = :city " +
                "where id = :id")
                .setParameter("name", venueBasicInfo.getName())
                .setParameter("city", venueBasicInfo.getCity())
                .setParameter("id", venueBasicInfo.getVenueId())
                .executeUpdate();

        return count > 0;
    }

    // SeatMap

    @Override
    public void addSeatMap(List<VenueSeat> seatMap) {
        Session session = sessionFactory.getCurrentSession();
        for (VenueSeat venueSeat : seatMap) {
            session.save(venueSeat);
        }
    }

    @Override
    public boolean updateSeatMap(VenueSeatInfoDTO venueSeatInfoDTO) {
        //todo 测试
        Session session = sessionFactory.getCurrentSession();
        for (VenueSeat venueSeat : venueSeatInfoDTO.getSeatMap()) {
            session.update(venueSeat);
        }
        return true;
    }

    @Override
    public void deleteSeatMap(int venueId) {
        //删除座位表中该场馆的所有座位
        sessionFactory.getCurrentSession()
                .createQuery("delete from VenueSeat where venueSeatId.venueId = :venueId")
                .setParameter("venueId", venueId)
                .executeUpdate();
    }

    // VenuePlan

    @Override
    public void addVenuePlan(VenuePlan venuePlan) {
        sessionFactory.getCurrentSession().save(venuePlan);
    }

    @Override
    public VenuePlan getVenuePlan(int venuePlanId) {
        return sessionFactory.getCurrentSession().get(VenuePlan.class, venuePlanId);
    }

}
