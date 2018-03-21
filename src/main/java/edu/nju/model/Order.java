package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONField;
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
public class Order {

    /**
     * 订单号
     */
    @Id
    @GeneratedValue
    private String orderId;

    /**
     * 订单创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 订单状态
     */
    private OrderStatus orderStatus;

    /**
     * 与会员多对一，外键为会员id
     */
    @ManyToOne
    @JoinColumn(name = "memberId", foreignKey = @ForeignKey(name = "FK_MEMBER"))
    private Member member;

    /**
     * 与场馆多对一，外键为场馆id
     */
    @ManyToOne
    @JoinColumn(name = "venueId", foreignKey = @ForeignKey(name = "FK_VENUE"))
    private Venue venue;

    /**
     * 与场馆计划的座位一对多
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VenuePlanSeat> venuePlanSeats = new ArrayList<>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderID) {
        this.orderId = orderID;
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

    public List<VenuePlanSeat> getVenuePlanSeats() {
        return venuePlanSeats;
    }

    public void setVenuePlanSeats(List<VenuePlanSeat> venuePlanSeats) {
        this.venuePlanSeats = venuePlanSeats;
    }
}
