package edu.nju.dao.impl;

import edu.nju.dao.VenueDao;
import edu.nju.dto.RowAndColumnDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.model.VenuePlanSeat;
import edu.nju.util.LocalDateTimeUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // SeatMap

    @Override
    public void deleteSeatMap(int venueId) {
        //删除座位表中该场馆的所有座位
        sessionFactory.getCurrentSession()
                .createQuery("delete from VenueSeat where venue.id = :venueId ")
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

    @Override
    public List<VenuePlan> getAllVenuePlan(int venueId) {
        return sessionFactory.getCurrentSession().createQuery("from VenuePlan where venue.id = :venueId", VenuePlan.class)
                .setParameter("venueId", venueId)
                .list();
    }

    @Override
    public void updateVenuePlan(VenuePlan venuePlan) {
        sessionFactory.getCurrentSession().update(venuePlan);
    }

    @Override
    public void deleteVenuePlan(VenuePlan venuePlan) {
        sessionFactory.getCurrentSession().delete(venuePlan);
    }

    @Override
    public int getComingVenuePlanTotalNum() {
        Session session = sessionFactory.getCurrentSession();
        //开始时间要在当前时间之后
        return session.createQuery("select count(id) from VenuePlan where begin > :timeNow", Long.class)
                .setParameter("timeNow", LocalDateTime.now())
                .getSingleResult().intValue();
    }

    @Override
    public List<VenuePlan> getComingVenuePlans() {
        Session session = sessionFactory.getCurrentSession();
        Query<VenuePlan> query = session.createQuery("from VenuePlan where begin > :timeNow", VenuePlan.class);
        return query.setParameter("timeNow", LocalDateTime.now()).list();

    }

    @Override
    public List<VenuePlanSeat> getSpecificSeats(Integer venuePlanId, List<RowAndColumnDTO> orderPlanSeats) {
        Session session = sessionFactory.getCurrentSession();
        Query<VenuePlanSeat> query = session.createQuery("from VenuePlanSeat " +
                "where venuePlan.id =:venuePlanId and row =:row and column =:column ", VenuePlanSeat.class);

        List<VenuePlanSeat> specificVenuePlanSeats = new ArrayList<>();
        orderPlanSeats.forEach(rowAndColumn ->
                specificVenuePlanSeats.add(query.setParameter("venuePlanId", venuePlanId)
                        .setParameter("row", rowAndColumn.getRow())
                        .setParameter("column", rowAndColumn.getColumn())
                        .getSingleResult())
        );

        return specificVenuePlanSeats;
    }

    @Override
    public VenuePlanSeat getVenuePlanSeat(int venuePlanId, int row, int column) {
        return sessionFactory.getCurrentSession()
                .createQuery("from VenuePlanSeat " +
                        "where venuePlan.id = :venuePlanId and row = :row and column = :column ", VenuePlanSeat.class)
                .setParameter("venuePlanId", venuePlanId)
                .setParameter("row", row)
                .setParameter("column", column)
                .getSingleResult();
    }

    @Override
    public List<Venue> getAuditingVenue() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Venue " +
                        "where auditing = true ", Venue.class)
                .list();
    }

    @Override
    public List<VenuePlan> getCompleteVenuePlans() {
        Session session = sessionFactory.getCurrentSession();
        //场馆计划结束时间早于当前时间，说明场馆计划已经结束
        Query<VenuePlan> query = session.createQuery("from VenuePlan where endTime <= :now and complete = false", VenuePlan.class);
        query.setParameter("now", LocalDateTimeUtil.nowTillMinute());
        return query.list();
    }

    @Override
    public List<VenuePlan> getNeedSendTickets(int sendTicketsWeek) {
        Session session = sessionFactory.getCurrentSession();
        Query<VenuePlan> query = session.createQuery("from VenuePlan where begin <= :needSendTime and sendTickets = false ", VenuePlan.class);
        //场馆计划开始时间早于当前时间+2周，
        query.setParameter("needSendTime", LocalDateTimeUtil.nowTillMinute().plusWeeks(2));
        return query.list();
    }

}
