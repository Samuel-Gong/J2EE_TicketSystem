package edu.nju.util;

/**
 * @author Shenmiu
 * @date 2018/03/04
 *
 * 演出类型
 */
public enum ShowType {

    CONCERT("音乐会"),
    DANCE("舞蹈"),
    DRAMA("话剧"),
    SOCCER("足球"),
    BASKETBALL("篮球");

    private String name;

    private ShowType(String name) {
        this.name = name;
    }
}
