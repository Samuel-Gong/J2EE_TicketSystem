package edu.nju.dto;

import java.util.Map;

/**
 * @author Shenmiu
 * @date 2018/03/28
 * <p>
 * 会员积分和优惠券的数据传输对象
 */
public class PointsAndCoupons {

    /**
     * 积分
     */
    private Integer points;

    /**
     * 优惠券的面额和张数
     */
    private Map<Integer, Integer> coupons;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Map<Integer, Integer> getCoupons() {
        return coupons;
    }

    public void setCoupons(Map<Integer, Integer> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "PointsAndCoupons{" +
                "points=" + points +
                ", coupons=" + coupons +
                '}';
    }
}
