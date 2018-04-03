package edu.nju.model;

import javax.persistence.*;

/**
 * @author Shenmiu
 * @date 2018/03/28
 * <p>
 * 优惠券
 */
@Entity
@Table(name = "coupon")
public class Coupon {

    /**
     * 优惠券id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 优惠券与优惠券类型多对一，多张优惠券可能是同一种类型，但是必须有一张类型
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "couponType", foreignKey = @ForeignKey(name = "FK_COUPON_TYPE"))
    private CouponType couponType;

    /**
     * 优惠券与会员多对一，多张优惠券可能属于同一个会员，但是必须属于某一个会员
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "member", foreignKey = @ForeignKey(name = "FK_MEMBER"))
    private Member memberFK;

    /**
     * 优惠券与订单，一对一，优惠券还未使用，即可不与order绑定
     */
    @OneToOne(mappedBy = "coupon")
    private Order order;

    /**
     * 是否已经使用
     */
    private boolean used = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public Member getMemberFK() {
        return memberFK;
    }

    public void setMemberFK(Member member) {
        this.memberFK = member;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
