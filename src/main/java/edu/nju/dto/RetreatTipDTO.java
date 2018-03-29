package edu.nju.dto;

/**
 * @author Shenmiu
 * @date 2018/03/29
 * <p>
 * 退订订单提示
 */
public class RetreatTipDTO {

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
    private Integer returnedMoney;

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

    public Integer getReturnedMoney() {
        return returnedMoney;
    }

    public void setReturnedMoney(Integer returnedMoney) {
        this.returnedMoney = returnedMoney;
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
                ", returnedMoney=" + returnedMoney +
                ", moreThanMaxDay=" + moreThanMaxDay +
                '}';
    }
}
