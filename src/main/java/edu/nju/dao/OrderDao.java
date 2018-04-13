package edu.nju.dao;

import edu.nju.model.Order;
import edu.nju.util.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * order数据访问接口
 */
public interface OrderDao extends JpaRepository<Order, Integer> {

    /**
     * 添加一条订单记录
     *
     * @param order 订单信息
     * @return 添加的订单记录
     */
    @Override
    <S extends Order> S save(S order);

    /**
     * 根据场馆计划编号来查找对应的订单
     *
     * @param venueId 场馆计划编号
     * @return 场馆计划对应的订单列表
     */
    List<Order> getOrdersByVenuePlanVenuePlanId(int venueId);

    /**
     * 根据订单编号获得订单持久化对象
     *
     * @param orderId 订单编号
     * @return 订单持久化对象
     */
    @Override
    Optional<Order> findById(Integer orderId);

    /**
     * 根据订单状态获取该状态的所有订单
     *
     * @param orderStatus 订单状态
     * @return 所有未支付订单列表
     */
    List<Order> getOrdersByOrderStatus(OrderStatus orderStatus);

    /**
     * 获取网上订单
     *
     * @param mail        会员邮箱
     * @param orderStatus 订单状态
     * @return 网上指定订单状态的订单列表
     */
    List<Order> getOrdersByMemberFkMailAndOrderStatusAndBoughtOnlineIsTrue(String mail, OrderStatus orderStatus);
}
