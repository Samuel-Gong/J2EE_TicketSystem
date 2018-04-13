package edu.nju.dao.impl;

import edu.nju.dao.VenueSeatDao;
import edu.nju.dto.RowAndColumnDTO;
import edu.nju.model.VenuePlanSeat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/04/13
 */
public class VenueSeatDaoImpl implements VenueSeatDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<VenuePlanSeat> getSpecificSeats(Integer venuePlanId, List<RowAndColumnDTO> orderPlanSeats) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<VenuePlanSeat> query = entityManager.createQuery("from VenuePlanSeat " +
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
        return entityManagerFactory.createEntityManager()
                .createQuery("from VenuePlanSeat " +
                        "where venuePlan.id = :venuePlanId and row = :row and column = :column ", VenuePlanSeat.class)
                .setParameter("venuePlanId", venuePlanId)
                .setParameter("row", row)
                .setParameter("column", column)
                .getSingleResult();
    }

    @Override
    public void deleteSeatMap(int venueId) {
        //删除座位表中该场馆的所有座位
        entityManagerFactory.createEntityManager()
                .createQuery("delete from VenueSeat where venue.id = :venueId ")
                .setParameter("venueId", venueId)
                .executeUpdate();
    }

}
