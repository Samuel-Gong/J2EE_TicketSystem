package edu.nju.util;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 订单状态
 */
public enum OrderStatus {

    /**
     * 等待配票
     */
    WAITING_TICKETS("等待配票"),
    /**
     * 已配票
     */
    ARRANGED("已配票"),
    /**
     * 已退订
     */
    RETREAT("已退订"),
    /**
     * 已消费
     */
    COMSUMPED("已消费");

    private String value;

    public static OrderStatus val2Status(String value) {
        if (WAITING_TICKETS.value.equals(value)) {
            return WAITING_TICKETS;
        }
        if (ARRANGED.value.equals(value)) {
            return ARRANGED;
        }
        if (RETREAT.value.equals(value)) {
            return RETREAT;
        }
        if (COMSUMPED.value.equals(value)) {
            return COMSUMPED;
        }
        return null;
    }

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
