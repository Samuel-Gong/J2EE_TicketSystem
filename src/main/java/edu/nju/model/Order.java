package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.util.LocalDateTimeUtil;
import edu.nju.util.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/04
 */
@Entity
@Table(name = "`order`")
@JSONType(ignores = {"member", "venue", "venuePlan"})
public class Order {

    /**
     * 订单号
     */
    @Id
    @GeneratedValue
    private Integer orderId;

    /**
     * 订单创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 立即购买选择的座位类型
     */
    private Character seatType;

    /**
     * 立即购买选用的座位数量
     */
    private Integer seatNum;

    /**
     * 订单总价
     */
    private int price;

    /**
     * 订单状态
     */
    @JSONField(deserialize = false)
    private OrderStatus orderStatus;

    /**
     * 座位是否固定
     */
    private boolean seatSettled;

    /**
     * 是否位置足够，默认未true
     */
    private boolean seatEnough = true;

    /**
     * 是否是线上购票
     */
    private boolean boughtOnline;

    /**
     * 是否是会员购票
     */
    private boolean memberOrder;

    /**
     * 预订的座位的字符串
     */
    private String bookedSeatStr;

    /**
     * 退还票价
     */
    private double returnedMoney;

    /**
     * 多个订单可能对应一个会员，外键为会员id，可以不对应会员：现场购票（非会员）
     */
    @ManyToOne
    @JoinColumn(name = "memberId", foreignKey = @ForeignKey(name = "FK_MEMBER"))
    private Member memberFK;

    /**
     * 多个订单可能对应一个场馆，与场馆多对一，外键为场馆id
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "venueId", foreignKey = @ForeignKey(name = "FK_VENUE"))
    private Venue venue;

    /**
     * 多个订单可能对应一个场馆计划，外键为场馆计划id
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "venuePlanId", foreignKey = @ForeignKey(name = "FK_VENUE_PLAN"))
    private VenuePlan venuePlan;

    /**
     * 一个order可能占有多个场馆座位
     */
    @OneToMany(mappedBy = "order")
    private List<VenuePlanSeat> venuePlanSeats = new ArrayList<>();

    public Order() {
    }

    public Order(TakeOrderDTO takeOrderDTO) {
        //设定为当前时间
        this.setCreateTime(LocalDateTimeUtil.nowTillMinute());
        //设置订单总价
        this.setPrice(takeOrderDTO.getPrice());
        //订单是自选座位
        this.setSeatSettled(takeOrderDTO.getSeatSettled());
        //订单是否是现场购票
        this.setBoughtOnline(takeOrderDTO.getBoughtOnline());
        //订单是否是会员购票
        this.setMemberOrder(takeOrderDTO.getMemberOrder());
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderID) {
        this.orderId = orderID;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public boolean isSeatSettled() {
        return seatSettled;
    }

    public void setSeatSettled(boolean pickSeat) {
        this.seatSettled = pickSeat;
    }

    public boolean isSeatEnough() {
        return seatEnough;
    }

    public void setSeatEnough(boolean seatEnough) {
        this.seatEnough = seatEnough;
    }

    public boolean isBoughtOnline() {
        return boughtOnline;
    }

    public void setBoughtOnline(boolean boughtOnSite) {
        this.boughtOnline = boughtOnSite;
    }

    public boolean isMemberOrder() {
        return memberOrder;
    }

    public void setMemberOrder(boolean memberOrder) {
        this.memberOrder = memberOrder;
    }

    public String getBookedSeatStr() {
        return bookedSeatStr;
    }

    public void setBookedSeatStr(String seatBooked) {
        this.bookedSeatStr = seatBooked;
    }

    public double getReturnedMoney() {
        return returnedMoney;
    }

    public void setReturnedMoney(double returnedMoney) {
        this.returnedMoney = returnedMoney;
    }

    public Member getMemberFK() {
        return memberFK;
    }

    public void setMemberFK(Member member) {
        this.memberFK = member;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public VenuePlan getVenuePlan() {
        return venuePlan;
    }

    public void setVenuePlan(VenuePlan venuePlan) {
        this.venuePlan = venuePlan;
    }

    public List<VenuePlanSeat> getVenuePlanSeats() {
        return venuePlanSeats;
    }

    public void setVenuePlanSeats(List<VenuePlanSeat> venuePlanSeats) {
        this.venuePlanSeats = venuePlanSeats;
    }
}
