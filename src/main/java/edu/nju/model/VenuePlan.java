package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import edu.nju.util.ShowType;
import edu.nju.util.deserializer.ShowTypeDeserializer;
import edu.nju.util.serializer.ShowTypeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 场馆计划
 */
@Entity
@Table(name = "venue_plan")
@JSONType(ignores = {"venue", "orders"}, orders = {"venuePlanId", "begin", "end", "showType", "description", "seatTypes", "venueSeats", "orders"})
public class VenuePlan {

    /**
     * 场馆计划的id
     * <p>
     * 使用主键自增的策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int venuePlanId;

    /**
     * 场馆计划开始时间，每个计划的开始时间不可以相同
     */
    @JSONField(name = "begin", format = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime begin;

    /**
     * 场馆计划结束时间，每个计划的结束时间不可以相同
     */
    @JSONField(name = "end", format = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime endTime;

    /**
     * 演出类型
     */
    @JSONField(deserializeUsing = ShowTypeDeserializer.class, serializeUsing = ShowTypeSerializer.class)
    private ShowType showType;

    /**
     * 简单描述
     */
    private String description;

    /**
     * 是否已经结束
     */
    private boolean complete;

    /**
     * 是否已经配票
     */
    private boolean sendTickets;

    /**
     * 是否已经结算
     */
    private boolean settle;

    /**
     * 总共的票价收入
     */
    private int totalIncome;

    /**
     * 实际经理结算的收入
     */
    private int actualIncome;

    /**
     * 与场馆多对一，外键为场馆编号
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "venueId", foreignKey = @ForeignKey(name = "FK_VENUE"), nullable = false)
    private Venue venue;

    /**
     * 与座位类型一对多
     */
    @OneToMany(mappedBy = "venuePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatType> seatTypes = new ArrayList<>();

    /**
     * 与场馆计划的座位一对多
     */
    @OneToMany(mappedBy = "venuePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VenuePlanSeat> venuePlanSeats = new ArrayList<>();

    /**
     * 与订单一对多
     */
    @OneToMany(mappedBy = "venuePlan")
    private List<Order> orders = new ArrayList<>();


    public int getVenuePlanId() {
        return venuePlanId;
    }

    public void setVenuePlanId(int venuePlanId) {
        this.venuePlanId = venuePlanId;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime end) {
        this.endTime = end;
    }

    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isSendTickets() {
        return sendTickets;
    }

    public void setSendTickets(boolean sendTickets) {
        this.sendTickets = sendTickets;
    }

    public boolean isSettle() {
        return settle;
    }

    public void setSettle(boolean settle) {
        this.settle = settle;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(int actualIncome) {
        this.actualIncome = actualIncome;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<SeatType> getSeatTypes() {
        return seatTypes;
    }

    public void setSeatTypes(List<SeatType> seatTypes) {
        this.seatTypes = seatTypes;
    }

    public List<VenuePlanSeat> getVenuePlanSeats() {
        return venuePlanSeats;
    }

    public void setVenuePlanSeats(List<VenuePlanSeat> venuePlanSeats) {
        this.venuePlanSeats = venuePlanSeats;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VenuePlan venuePlan = (VenuePlan) o;

        if (!begin.equals(venuePlan.begin)) {
            return false;
        }
        if (!endTime.equals(venuePlan.endTime)) {
            return false;
        }
        return venue.equals(venuePlan.venue);
    }

    @Override
    public int hashCode() {
        int result = begin.hashCode();
        result = 31 * result + endTime.hashCode();
        result = 31 * result + venue.hashCode();
        return result;
    }
}
