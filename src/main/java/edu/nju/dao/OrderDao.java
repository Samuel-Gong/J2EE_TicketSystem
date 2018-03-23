package edu.nju.dao;

import edu.nju.model.Order;
import edu.nju.util.OrderStatus;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * order数据访问接口
 */
public interface OrderDao {

    /**
     * 添加一条订单记录
     *
     * @param order 订单信息
     */
    void addOrder(Order order);

    /**
     * 根据会员邮箱和订单状态获取订单列表
     *
     * @param mail   会员邮箱
     * @param orderStatus 订单状态
     * @return
     */
    List<Order> getOrders(String mail, OrderStatus orderStatus);
}
