package edu.nju.util;

/**
 * @author Shenmiu
 * @date 2018/03/27
 * <p>
 * 表示订单座位的状态：
 * 已配票
 * 未配票
 * 余票不足
 */
public enum OrderSeatStatus {


    ALREADY_WITH_TICKETS("已配票"),
    NOT_WITH_TICKETS("未配票"),
    TICKETS_NOT_ENOUGH("余票不足");

    private String value;

    OrderSeatStatus(String value) {
        this.value = value;
    }

    public static OrderSeatStatus val2Status(String value) {
        if (ALREADY_WITH_TICKETS.value.equals(value)) {
            return ALREADY_WITH_TICKETS;
        }
        if (NOT_WITH_TICKETS.value.equals(value)) {
            return NOT_WITH_TICKETS;
        }
        if (TICKETS_NOT_ENOUGH.value.equals(value)) {
            return TICKETS_NOT_ENOUGH;
        }
        return null;
    }

    public String getValue() {
        return value;
    }


}
