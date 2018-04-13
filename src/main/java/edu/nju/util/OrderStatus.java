package edu.nju.util;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 订单状态
 */
public enum OrderStatus {

    /**
     * 未支付
     */
    UNPAID("未支付"),
    /**
     * 在15分钟内没有支付成功，已过期
     */
    EXPIRED("已过期"),
    /**
     * 未支付订单情况下取消支付
     */
    CANCELED("已取消"),
    /**
     * 已预订
     */
    BOOKED("已预订"),
    /**
     * 已消费
     */
    CONSUMED("已消费"),
    /**
     * 已退订
     */
    RETREAT("已退订");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public static OrderStatus val2Status(String value) {
        if (UNPAID.value.equals(value)) {
            return UNPAID;
        }
        if (EXPIRED.value.equals(value)) {
            return EXPIRED;
        }
        if (CANCELED.value.equals(value)) {
            return CANCELED;
        }
        if (BOOKED.value.equals(value)) {
            return BOOKED;
        }
        if (CONSUMED.value.equals(value)) {
            return CONSUMED;
        }
        if (RETREAT.value.equals(value)) {
            return RETREAT;
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
