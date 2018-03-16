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
    private String orderId;

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

    //TODO
//    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<VenueSeat> seatList = new ArrayList<>();

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
}
