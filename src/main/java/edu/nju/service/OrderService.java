package edu.nju.service;

import edu.nju.dto.MemberOrderDTO;

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
     * @param memberOrderDTO 用户订单的数据传输对象
     * @return 是否添加成功
     */
    boolean addPickSeatOrder(MemberOrderDTO memberOrderDTO);

    /**
     * 添加一个用户立即购买的订单记录
     * @param memberOrderDTO 用户订单的数据传输对象
     * @return 是否添加成功
     */
    boolean addBuyNowOrder(MemberOrderDTO memberOrderDTO);
}
