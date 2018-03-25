package edu.nju.job;

import edu.nju.service.OrderService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

/**
 * @author Shenmiu
 * @date 2018/03/24
 * <p>
 * 根据JobDataMap中的订单号，检查该订单是否是未支付订单，若是，就把订单状态设置为已过期，检查时间在Trigger中指定
 */
public class UnpaidOrderCheckJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        int orderId = dataMap.getInt("orderId");
        OrderService orderService = (OrderService) dataMap.get("orderService");
        System.out.println(orderId);
        if (orderService == null) {
            System.out.println("null service");
        } else {
            orderService.checkUnpaidOrder(orderId);
            System.out.println("订单状态已被修改");
        }
    }
}
