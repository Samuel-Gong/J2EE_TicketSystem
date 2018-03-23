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

    private String value;

    public static OrderStatus val2Status(String value) {
        if(UNPAID.value.equals(value)){
            return UNPAID;
        }
        if (BOOKED.value.equals(value)) {
            return BOOKED;
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
