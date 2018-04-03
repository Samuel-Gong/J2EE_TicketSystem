package edu.nju.dto;

/**
 * @author Shenmiu
 * @date 2018/04/03
 * <p>
 * * 退订订单提示
 */
public class RefundTipDTO {

    /**
     * 距离场馆计划开始不足的天数
     */
    private Integer milestoneDay;

    /**
     * 手续费费率
     */
    private Integer feeRate;

    /**
     * 当前总价
     */
    private Integer actualPrice;

    /**
     * 应退还的钱
     */
    private Integer refund;

    /**
     * 是否比最大的天数还要大
     */
    private Boolean moreThanMaxDay;

    public Integer getMilestoneDay() {
        return milestoneDay;
    }

    public void setMilestoneDay(Integer milestoneDay) {
        this.milestoneDay = milestoneDay;
    }

    public Integer getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(Integer feeRate) {
        this.feeRate = feeRate;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getRefund() {
        return refund;
    }

    public void setRefund(Integer refund) {
        this.refund = refund;
    }

    public Boolean getMoreThanMaxDay() {
        return moreThanMaxDay;
    }

    public void setMoreThanMaxDay(Boolean moreThanMaxDay) {
        this.moreThanMaxDay = moreThanMaxDay;
    }

    @Override
    public String toString() {
        return "RetreatTipDTO{" +
                "milestoneDay=" + milestoneDay +
                ", feeRate=" + feeRate +
                ", actualPrice=" + actualPrice +
                ", refund=" + refund +
                ", moreThanMaxDay=" + moreThanMaxDay +
                '}';
    }

}
