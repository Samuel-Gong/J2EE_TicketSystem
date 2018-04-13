package edu.nju.dao;

import edu.nju.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Shenmiu
 * @date 2018/03/29
 * <p>
 * 支付宝账户的数据访问接口
 */
public interface AccountDao extends JpaRepository<Account, Integer> {

    /**
     * 根据支付宝账户的id获取账户
     *
     * @param accountId 支付宝账户id
     * @return 支付宝账户
     */
    @Override
    Account getOne(Integer accountId);

}
