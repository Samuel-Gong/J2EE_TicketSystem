package edu.nju.dao;

import edu.nju.model.Account;

/**
 * @author Shenmiu
 * @date 2018/03/29
 * <p>
 * 支付宝账户的数据访问接口
 */
public interface AccountDao {

    /**
     * 根据支付宝账户的id获取账户
     *
     * @param accountId 支付宝账户id
     * @return 支付宝账户
     */
    Account getAccount(int accountId);

}
