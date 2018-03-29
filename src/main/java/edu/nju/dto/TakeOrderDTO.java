package edu.nju.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * 会员下订单时的数据传输对象
 */
public class TakeOrderDTO {

    /**
     * 会员邮箱
     */
    private String mail;

    /**
     * 场馆编号
     */
    private Integer venueId;

    /**
     * 场馆计划编号
     */
    private Integer venuePlanId;

    /**
     * 订单创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 被订单选中的所有场馆计划的座位
     */
    private List<RowAndColumnDTO> orderSeats;

    /**
     * 立即购买选择的座位类型
     */
    private Character seatType;

    /**
     * 立即购买选用的座位数量
     */
    private Integer seatNum;

    /**
     * 订单原价格
     */
    private Integer price;

    /**
     * 订单优惠价格
     */
    private Integer actualPrice;

    /**
     * 座位是否选择
     */
    private Boolean seatSettled;

    /**
     * 是否是在线上购票
     */
    private Boolean boughtOnline;

    /**
     * 是否是会员订票
     */
    private Boolean memberOrder;

    /**
     * 是否使用会员折扣
     */
    private boolean memberDiscount;

    /**
     * 是否使用优惠券
     */
    private boolean useCoupon;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getVenuePlanId() {
        return venuePlanId;
    }

    public void setVenuePlanId(Integer venuePlanId) {
        this.venuePlanId = venuePlanId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<RowAndColumnDTO> getOrderSeats() {
        return orderSeats;
    }

    public void setOrderSeats(List<RowAndColumnDTO> orderSeats) {
        this.orderSeats = orderSeats;
    }

    public Character getSeatType() {
        return seatType;
    }

    public void setSeatType(Character seatType) {
        this.seatType = seatType;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Boolean getSeatSettled() {
        return seatSettled;
    }

    public void setSeatSettled(Boolean seatSettled) {
        this.seatSettled = seatSettled;
    }

    public void setMemberOrder(Boolean memberOrder) {
        this.memberOrder = memberOrder;
    }

    public Boolean getBoughtOnline() {
        return boughtOnline;
    }

    public void setBoughtOnline(Boolean boughtOnline) {
        this.boughtOnline = boughtOnline;
    }

    public boolean getMemberOrder() {
        return memberOrder;
    }

    public void setMemberOrder(boolean member) {
        memberOrder = member;
    }

    public boolean isMemberDiscount() {
        return memberDiscount;
    }

    public void setMemberDiscount(boolean memberDiscount) {
        this.memberDiscount = memberDiscount;
    }

    public boolean isUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(boolean useCoupon) {
        this.useCoupon = useCoupon;
    }

    @Override
    public String toString() {
        return "TakeOrderDTO{" +
                "mail='" + mail + '\'' +
                ", venueId=" + venueId +
                ", venuePlanId=" + venuePlanId +
                ", createTime=" + createTime +
                ", orderSeats=" + orderSeats +
                ", seatType=" + seatType +
                ", seatNum=" + seatNum +
                ", price=" + price +
                ", seatSettled=" + seatSettled +
                ", boughtOnline=" + boughtOnline +
                ", memberOrder=" + memberOrder +
                ", memberDiscount=" + memberDiscount +
                ", useCoupon=" + useCoupon +
                '}';
    }
}
