package edu.nju.dto;

/**
 * @author Shenmiu
 * @date 2018/03/31
 * <p>
 * 场馆统计数据传输对象
 */
public class VenueFinance {

    /**
     * 总预订票价
     */
    private Integer totalBooked;

    /**
     * 总退订票价
     */
    private Integer totalRefund;

    /**
     * 总结算票价
     */
    private Integer totalSettle;

    /**
     * 总未结算票价
     */
    private Integer totalUnsettle;


    public VenueFinance(int totalBooked, int totalRefund, int totalSettle, int totalUnsettle) {
        this.totalBooked = totalBooked;
        this.totalRefund = totalRefund;
        this.totalSettle = totalSettle;
        this.totalUnsettle = totalUnsettle;
    }

    public Integer getTotalBooked() {
        return totalBooked;
    }

    public void setTotalBooked(Integer totalBooked) {
        this.totalBooked = totalBooked;
    }

    public Integer getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Integer totalRefund) {
        this.totalRefund = totalRefund;
    }

    public Integer getTotalSettle() {
        return totalSettle;
    }

    public void setTotalSettle(Integer totalSettle) {
        this.totalSettle = totalSettle;
    }

    public Integer getTotalUnsettle() {
        return totalUnsettle;
    }

    public void setTotalUnsettle(Integer totalUnsettle) {
        this.totalUnsettle = totalUnsettle;
    }
}
