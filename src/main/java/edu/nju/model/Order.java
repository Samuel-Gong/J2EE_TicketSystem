package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONField;
import edu.nju.util.OrderStatus;
import org.hibernate.annotations.Cascade;

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
    private Integer orderId;

    /**
     * 订单创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 订单状态
     */
    @JSONField(deserialize = false)
    private OrderStatus orderStatus;

    /**
     * 与会员多对一，外键为会员id
     */
    @JSONField(deserialize = false, serialize = false)
    @ManyToOne
    @JoinColumn(name = "memberId", foreignKey = @ForeignKey(name = "FK_MEMBER"))
    private Member member;

    /**
     * 与场馆多对一，外键为场馆id
     */
    @JSONField(deserialize = false, serialize = false)
    @ManyToOne
    @JoinColumn(name = "venueId", foreignKey = @ForeignKey(name = "FK_VENUE"))
    private Venue venue;

    /**
     * 与场馆计划的座位一对多
     */
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<VenuePlanSeat> venuePlanSeats = new ArrayList<>();

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderID) {
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
