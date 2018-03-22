package edu.nju.util;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 演出类型
 */
public enum ShowType {

    /**
     * 演唱会
     */
    CONCERT("音乐会"),
    /**
     * 舞蹈
     */
    DANCE("舞蹈"),
    /**
     * 话剧
     */
    DRAMA("话剧"),
    /**
     * 体育比赛
     */
    SPORTS("体育比赛");

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
        if (SPORTS.value.equals(value)) {
            return SPORTS;
        }
        return null;
    }

    private final String value;

    ShowType(String value) {
        this.value = value;
    }

}
