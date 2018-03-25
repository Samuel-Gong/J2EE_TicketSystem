package edu.nju.service;

import java.time.LocalDateTime;

/**
 * @author Shenmiu
 * @date 2018/03/24
 */
public interface JobService {

    /**
     * 根据订单新创建的时间和订单编号，新增一个检查订单未支付状态的任务
     *
     * @param createTime 订单创建时间
     * @param orderId    订单编号
     */
    void addCheckUnpaidOrderJob(LocalDateTime createTime, int orderId);

}
