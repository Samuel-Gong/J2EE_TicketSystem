package edu.nju.dto;

/**
 * @author Shenmiu
 * @date 2018/03/25
 * <p>
 * 显示会员的等级和折扣信息
 */
public class LevelAndDiscount {

    /**
     * 会员等级
     */
    Integer level;

    /**
     * 会员折扣
     */
    Integer discount;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "LevelAndDiscount{" +
                "level=" + level +
                ", discount=" + discount +
                '}';
    }
}
