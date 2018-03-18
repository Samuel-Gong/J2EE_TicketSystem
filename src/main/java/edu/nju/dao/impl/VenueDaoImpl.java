package edu.nju.dao.impl;

import edu.nju.dao.VenueDao;
import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenueSeat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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

    @Override
    public Venue getVenue(int venueId) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Venue venue = session.createQuery("from Venue where id = :id", Venue.class)
                .setParameter("id", venueId).uniqueResult();

        assert venue != null;

        tx.commit();

        return venue;
    }

    @Override
    public String getPassword(int venueId) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        String password = session.createQuery("select password from Venue where id = :id", String.class)
                .setParameter("id", venueId)
                .getSingleResult();

        tx.commit();

        return password;
    }

    @Override
    public boolean addVenue(Venue venue) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        for (VenueSeat venueSeat : venue.getSeatMap()) {
            venueSeat.setVenue(venue);
        }

        session.save(venue);

        tx.commit();

        return true;
    }

    @Override
    public boolean updateBasicInfo(VenueBasicInfoDTO venueBasicInfo) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        int count = session.createQuery("update Venue set name = :name, city = :city " +
                "where id = :id")
                .setParameter("name", venueBasicInfo.getName())
                .setParameter("city", venueBasicInfo.getCity())
                .setParameter("id", venueBasicInfo.getVenueId())
                .executeUpdate();

        tx.commit();

        return count > 0;
    }

    @Override
    public boolean updateSeatMap(VenueSeatInfoDTO venueSeatInfo) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        //更新场馆的行和列
        int venueUpdateRow = session.createQuery("update Venue set rowNum = :rowNum, columnNum = :columnNum " +
                "where id = :id")
                .setParameter("rowNum", venueSeatInfo.getRowNum())
                .setParameter("columnNum", venueSeatInfo.getColumnNum())
                .setParameter("id", venueSeatInfo.getVenueId())
                .executeUpdate();

        //删除座位表中该场馆的所有座位
        int deleteCount = session.createQuery("delete from VenueSeat where venueSeatId.venueId = :venueId")
                .setParameter("venueId", venueSeatInfo.getVenueId())
                .executeUpdate();

        Venue venue = session.get(Venue.class, venueSeatInfo.getVenueId());
        for (VenueSeat venueSeat : venueSeatInfo.getSeatMap()) {
            venueSeat.setVenue(venue);
            session.save(venueSeat);
        }

        tx.commit();

        return venueUpdateRow > 0 && deleteCount > 0;
    }

}
