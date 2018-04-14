package edu.nju.dto;

/**
 * @author Shenmiu
 * @date 2018/03/25
 * <p>
 * 显示会员的等级和折扣信息
 */
public class LevelAndDiscountDTO {

    /**
     * 会员等级
     */
    private Integer level;

    /**
     * 会员折扣
     */
    private Integer discount;

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
        return "LevelAndDiscountDTO{" +
                "level=" + level +
                ", discount=" + discount +
                '}';
    }
}
