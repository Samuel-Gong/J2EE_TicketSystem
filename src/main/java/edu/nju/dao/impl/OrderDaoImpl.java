package edu.nju.dao.impl;

import edu.nju.dao.OrderDao;
import edu.nju.model.Order;
import edu.nju.util.OrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * Order数据访问对象实现
 */
@Service("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public List<Order> getOrders(String mail, OrderStatus orderStatus) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Order where memberFK.mail = :mail and orderStatus = :orderStatus ", Order.class)
                .setParameter("mail", mail)
                .setParameter("orderStatus", orderStatus)
                .list();
    }

    @Override
    public List<Order> getOrderByMemberId(String memberId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Order where memberFK.mail = :mail ", Order.class)
                .setParameter("mail", memberId)
                .list();
    }

    @Override
    public List<Order> getOrdersByVenuePlanId(int vneuePlanId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Order where venuePlan.id = :venuePlanId ", Order.class)
                .setParameter("venuePlanId", vneuePlanId)
                .list();
    }

    @Override
    public Order getOrder(int orderId) {
        return sessionFactory.getCurrentSession().get(Order.class, orderId);
    }
}
