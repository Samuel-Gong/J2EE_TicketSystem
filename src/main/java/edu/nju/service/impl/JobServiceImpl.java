package edu.nju.service.impl;

import edu.nju.job.UnpaidOrderCheckJob;
import edu.nju.service.JobService;
import edu.nju.service.OrderService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Shenmiu
 * @date 2018/03/24
 */
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private OrderService orderService;

    /**
     * 订单支付失效时间
     */
    private final int EXPIRE_MINUTE = 15;

    @Override
    public void addCheckUnpaidOrderJob(LocalDateTime createTime, int orderId) {
        //create first JobDetail and Trigger
        JobDetail jobDetail = JobBuilder.newJob(UnpaidOrderCheckJob.class)
                .withIdentity("unpaidOrder", "orderCheck")
                .build();
        //add passing parameters to JobDataMap for first JobDetail
        jobDetail.getJobDataMap().put("orderId", orderId);
        jobDetail.getJobDataMap().put("orderService", orderService);

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("unpaidOrderTrigger", "orderCheck")
                .startNow()
                //设置开始时间
                .startAt(getStartTime(createTime))
                //不设置interval、repeatCount，都默认为0
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();

        Scheduler scheduler = null;
        try {
            StdSchedulerFactory factory = new StdSchedulerFactory();
            factory.initialize(new ClassPathResource("quartz.properties").getInputStream());

            scheduler = factory.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date getStartTime(LocalDateTime createTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime expireTime = createTime.plusMinutes(EXPIRE_MINUTE);
        ZonedDateTime zdt = expireTime.atZone(zoneId);

        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDateTime = " + expireTime);
        System.out.println("Date = " + date);

        return date;
    }
}
