package edu.nju.service.strategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Shenmiu
 * @date 2018/03/29
 * <p>
 * 退款策略
 */
public class RetreatStrategy {

    /**
     * 距离演出开始天数
     */
    private final static int[] MILESTONE_DAYS = {5, 10, 20};

    /**
     * 天数对应的退款折扣
     */
    private final static int[] FEE_RATE = {100, 70, 50, 0};

    /**
     * 根据场馆计划开始时间，计算当前应该退款多少
     *
     * @param planBeginTime 场馆计划开始时间
     * @param totalMoney    订单总价
     * @return 应该退款的数目
     */
    public static int calculateReturnMoney(LocalDateTime planBeginTime, int totalMoney) {
        int feeRateIndex = findMilestoneDay(planBeginTime);

        //找到应收手续费费率，总退款费用
        int feeRate = FEE_RATE[feeRateIndex];

        return totalMoney * 100 / (100 - feeRate);
    }

    private static int findMilestoneDay(LocalDateTime planBeginTime) {
        long remainDay = LocalDateTime.now().until(planBeginTime, ChronoUnit.DAYS);
        int index = 0;
        while (remainDay > MILESTONE_DAYS[index]) {
            index++;
        }
        return index;
    }

}
