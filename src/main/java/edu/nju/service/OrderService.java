package edu.nju.service;

import edu.nju.dto.OrderShowDTO;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.util.OrderStatus;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 添加一个用户选座购买的订单记录
     *
     * @param takeOrderDTO 用户订单的数据传输对象
     * @return 添加的订单编号
     */
    int addPickSeatOrder(TakeOrderDTO takeOrderDTO);

    /**
     * 添加一个用户立即购买的订单记录
     *
     * @param takeOrderDTO 用户订单的数据传输对象
     * @return 添加的订单编号
     */
    int addBuyNowOrder(TakeOrderDTO takeOrderDTO);

    /**
     * 根据用户邮箱、订单状态，获取未支付订单
     *
     * @param mail        用户邮箱
     * @param orderStatus 订单状态
     * @return 指定订单状态的订单列表
     */
    List<OrderShowDTO> getOrderShowDTOs(String mail, OrderStatus orderStatus);

    /**
     * 根据订单编号检查订单是否为未支付订单，若是未支付订单，则将订单状态改为已过期
     *
     * @param orderId 订单编号
     */
    void checkUnpaidOrder(int orderId);

    /**
     * 根据订单编号获取订单展示信息
     *
     * @param orderId 订单编号
     * @return 订单信息
     */
    OrderShowDTO getOrderShowDTO(int orderId);

    /**
     * 支付订单
     *
     * @param mail    会员邮箱
     * @param orderId 订单编号
     * @return 支付是否成功
     */
    boolean payOrder(String mail, int orderId);

    /**
     * 取消订单支付
     *
     * @param orderId 订单编号
     * @return 取消是否成功
     */
    boolean cancelOrder(int orderId);

    /**
     * 退订订单
     *
     * @param mail    会员邮箱
     * @param orderId 订单编号
     * @return 退订是否成功
     */
    boolean retreatOrder(String mail, int orderId);

    /**
     * 场馆现场购票
     * @param takeOrderDTO 用户订单的数据传输对象
     * @return 购票是否成功
     */
    boolean takeOrderOnSite(TakeOrderDTO takeOrderDTO);
}
