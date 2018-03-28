package edu.nju.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/28
 * <p>
 * 优惠券类型
 */
@Entity
@Table(name = "coupon_type")
public class CouponType {

    /**
     * 优惠券面值，优惠券面值座位id
     */
    @Id
    private int value;

    /**
     * 兑换所需积分
     */
    private int requiredPoints;

    @OneToMany(mappedBy = "couponType")
    private List<Coupon> coupon;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }
}
