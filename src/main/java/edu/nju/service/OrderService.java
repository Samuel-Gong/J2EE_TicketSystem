package edu.nju.service;

import edu.nju.dto.OrderShowDTO;
import edu.nju.dto.TakeOrderDTO;

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
     * @return 是否添加成功
     */
    boolean addPickSeatOrder(TakeOrderDTO takeOrderDTO);

    /**
     * 添加一个用户立即购买的订单记录
     * @param takeOrderDTO 用户订单的数据传输对象
     * @return 是否添加成功
     */
    boolean addBuyNowOrder(TakeOrderDTO takeOrderDTO);

    /**
     * 获取未支付订单
     * @param mail 用户邮箱
     * @return 未支付订单列表
     */
    List<OrderShowDTO> getUnpaidOrdersWithShowInfo(String mail);
}
