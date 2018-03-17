package edu.nju.util;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 演出类型
 */
public enum ShowType {

    CONCERT("演唱会"),
    DANCE("舞蹈"),
    DRAMA("话剧"),
    SOCCER("足球"),
    BASKETBALL("篮球"),
    NULL("未知");

    public String getValue() {
        return value;
    }

    public static ShowType val2name(String value) {
        if (CONCERT.value.equals(value)) {
            return CONCERT;
        }
        if (DANCE.value.equals(value)) {
            return DANCE;
        }
        if (DRAMA.value.equals(value)) {
            return DRAMA;
        }
        if (SOCCER.value.equals(value)) {
            return SOCCER;
        }
        if (BASKETBALL.value.equals(value)) {
            return BASKETBALL;
        }
        return NULL;
    }

    private final String value;

    ShowType(String value) {
        this.value = value;
    }

}
