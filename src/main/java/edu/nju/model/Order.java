package edu.nju.model;

import edu.nju.util.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String orderID;

    /**
     * 会员号
     */
    private int memberID;

    /**
     * 7位场馆编号
     */
    @Column(length = 7)
    private String venueID;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
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
}
