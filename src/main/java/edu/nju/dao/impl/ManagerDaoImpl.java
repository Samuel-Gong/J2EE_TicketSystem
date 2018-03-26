package edu.nju.dao.impl;

import edu.nju.dao.ManagerDao;
import edu.nju.model.Manager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 经理数据访问接口实现
 */
@Repository("managerDao")
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Manager getManager(int id) {
        return sessionFactory.getCurrentSession().get(Manager.class, id);
    }
}
