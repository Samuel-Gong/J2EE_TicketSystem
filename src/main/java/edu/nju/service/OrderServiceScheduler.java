package edu.nju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 对订单的定时任务
 */
@Service("orderServiceScheduler")
public class OrderServiceScheduler {

    @Autowired
    private OrderService orderService;

    /**
     * 定时检查所有未支付订单，如果订单超时，则将订单设置为过期订单
     * 时间间隔为5s检查一次
     */
    @Scheduled(fixedRate = 5000)
    public void checkUnpaidOrders() {
        orderService.checkUnpaidOrders();
    }

}
