package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
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
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

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
     * 多个订单可能对应一个会员，外键为会员id
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "memberId", foreignKey = @ForeignKey(name = "FK_MEMBER"))
    private Member member;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderID) {
        this.orderId = orderID;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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
