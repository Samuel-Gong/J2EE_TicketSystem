package edu.nju.service.strategy;

import edu.nju.dto.RefundTipDTO;

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
    private final static int[] MILESTONE_DAYS = {5, 10, 14};

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
    public static RefundTipDTO calculateRefund(LocalDateTime planBeginTime, int totalMoney) {
        RefundTipDTO refundTipDTO = new RefundTipDTO();

        //退款折扣的index
        int feeRateIndex = findMilestoneDay(planBeginTime);

        //比最大的天数还大
        if (feeRateIndex == MILESTONE_DAYS.length) {
            refundTipDTO.setMoreThanMaxDay(true);
            refundTipDTO.setMilestoneDay(MILESTONE_DAYS[feeRateIndex - 1]);
        }
        //没有最大天数大
        else {
            refundTipDTO.setMoreThanMaxDay(false);
            refundTipDTO.setMilestoneDay(MILESTONE_DAYS[feeRateIndex]);
        }

        //找到应收手续费费率，总退款费用
        int feeRate = FEE_RATE[feeRateIndex];
        refundTipDTO.setFeeRate(feeRate);

        //设置原价
        refundTipDTO.setActualPrice(totalMoney);
        //设置该退还的钱
        refundTipDTO.setRefund(totalMoney * (100 - feeRate) / 100);

        return refundTipDTO;
    }

    private static int findMilestoneDay(LocalDateTime planBeginTime) {
        long remainDay = LocalDateTime.now().until(planBeginTime, ChronoUnit.DAYS);
        int index = 0;
        while (index < MILESTONE_DAYS.length && remainDay >= MILESTONE_DAYS[index]) {
            index++;
        }
        return index;
    }

}
