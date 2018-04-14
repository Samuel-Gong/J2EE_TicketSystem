package edu.nju.dto;

/**
 * @author Shenmiu
 * @date 2018/03/31
 * <p>
 * 会员统计数据传输对象
 */
public class MemberStatisticsDTO {

    /**
     * 总预订
     */
    private Integer totalBooked;

    /**
     * 总消费
     */
    private Integer totalConsumed;

    /**
     * 总手续费
     */
    private Integer totalFee;

    /**
     * 总退还金额
     */
    private Integer totalRefund;

    public MemberStatisticsDTO(int totalBooked, int totalConsumed, int totalRefund, int totalFee) {
        this.totalBooked = totalBooked;
        this.totalConsumed = totalConsumed;
        this.totalRefund = totalRefund;
        this.totalFee = totalFee;
    }


    public Integer getTotalBooked() {
        return totalBooked;
    }

    public void setTotalBooked(Integer totalBooked) {
        this.totalBooked = totalBooked;
    }

    public Integer getTotalConsumed() {
        return totalConsumed;
    }

    public void setTotalConsumed(Integer totalConsumed) {
        this.totalConsumed = totalConsumed;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Integer totalRefund) {
        this.totalRefund = totalRefund;
    }
}
