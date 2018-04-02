package edu.nju.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 对订单的定时任务
 */
@Service("schedulerService")
public class SchedulerService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private VenueService venueService;

    /**
     * 定时检查所有未支付订单，如果订单超时，则将订单设置为过期订单
     * 时间间隔为5s检查一次
     */
//    @Scheduled(fixedRate = 5000)
    public void checkUnpaidOrders() {
        orderService.checkUnpaidOrders();
    }

    /**
     * 定时检查所有需要配送票的场馆计划，对距离演出开始不足两周的计划，开始配票
     * 每5分钟检查一次
     */
//    @Scheduled(cron = "0 0/10 * * * ?")
    public void sendTickets() {
        venueService.sendTickets();
    }

    /**
     * 定时检查已经结束的场馆计划
     * 每15分钟检查一次
     */
//    @Scheduled(cron = "0 0/15 * * * ?")
    public void consumeOrder() {
        venueService.checkCompleteVenuePlans();
    }

}
