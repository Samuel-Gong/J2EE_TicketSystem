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
        //todo 怎么级联查询，对比会员的账号
        return session.createQuery("from Order order left join fetch order.member m with m.mail = :mail where order.orderStatus = :orderStatus", Order.class)
                .setParameter("mail", mail)
                .setParameter("orderStatus", orderStatus)
                .list();
    }
}
