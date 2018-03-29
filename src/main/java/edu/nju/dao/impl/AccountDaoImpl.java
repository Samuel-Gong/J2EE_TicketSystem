package edu.nju.dao.impl;

import edu.nju.dao.AccountDao;
import edu.nju.model.Account;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Shenmiu
 * @date 2018/03/29
 * <p>
 * 支付宝账户数据接口实现
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account getAccount(int accountId) {
        return sessionFactory.getCurrentSession().get(Account.class, accountId);
    }
}
