package edu.nju.dao;

import edu.nju.model.Order; /**
 * @author Shenmiu
 * @date 2018/03/22
 *
 * order数据访问接口
 */
public interface OrderDao {

    /**
     * 添加一条订单记录
     * @param order 订单信息
     */
    void addOrder(Order order);
}
