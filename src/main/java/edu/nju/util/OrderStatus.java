package edu.nju.util;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 订单状态
 */
public enum OrderStatus {

    /**
     * 已预订
     */
    BOOKED("已预订"),
    /**
     * 已退订
     */
    RETREAT("已退订"),
    /**
     * 已消费
     */
    COMSUMPED("已消费");

    private String status;

    private OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
