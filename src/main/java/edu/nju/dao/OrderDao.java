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
     * @param mail        会员邮箱
     * @param orderStatus 订单状态
     * @return 特定状态的订单列表
     */
    List<Order> getOrders(String mail, OrderStatus orderStatus);

    /**
     * 根据会员邮箱来查找对应订单
     *
     * @param memberId 会员邮箱
     * @return 会员的所有订单
     */
    List<Order> getOrderByMemberId(String memberId);

    /**
     * 根据场馆计划编号来查找对应的订单
     *
     * @param vneuePlanId 场馆计划编号
     * @return 场馆计划对应的订单列表
     */
    List<Order> getOrdersByVenuePlanId(int vneuePlanId);

    /**
     * 根据订单编号获得订单持久化对象
     *
     * @param orderId 订单编号
     * @return 订单持久化对象
     */
    Order getOrder(int orderId);
}
