package edu.nju.service;

import edu.nju.dto.OrderShowDTO;
import edu.nju.dto.RefundTipDTO;
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
     * 根据用户邮箱、订单状态，获取未支付订单
     *
     * @param mail        用户邮箱
     * @param orderStatus 订单状态
     * @return 指定订单状态的订单列表
     */
    List<OrderShowDTO> getOrderShowDTOs(String mail, OrderStatus orderStatus);

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
     * 获取退订订单的提示
     *
     * @param mail    会员邮箱
     * @param orderId 订单编号
     * @return 退订订单提示数据传输对象
     */
    RefundTipDTO getRetreatOrderTip(String mail, int orderId);

    /**
     * 检查所有未支付订单，将超时的未支付订单设置为已过期
     */
    void checkUnpaidOrders();

    /**
     * 添加一个订单
     *
     * @param takeOrderDTO 订单数据传输对象
     * @return 添加的订单编号
     */
    int addOrder(TakeOrderDTO takeOrderDTO);

    /**
     * 退订订单
     *
     * @param mail    会员邮箱
     * @param orderId 订单编号
     * @param refund
     * @return 退订是否成功
     */
    boolean refund(String mail, int orderId, int refund);
}
