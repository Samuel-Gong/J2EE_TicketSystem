package edu.nju.dao.impl;

import edu.nju.dao.OrderDao;
import edu.nju.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
