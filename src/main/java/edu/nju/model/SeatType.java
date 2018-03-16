package edu.nju.model;

import edu.nju.model.embeddable.SeatTypeId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/15
 *
 * 表示某个场馆计划的座位类型
 */
@Entity
@Table(name = "seat_type")
public class SeatType {

    /**
     * 联合主键:
     * venuePlanId  场馆计划编号
     * typeChar     表示座位类型的字符
     */
    @EmbeddedId
    private SeatTypeId seatTypeId;

    /**
     * 该类型座位的价格
     */
    private int price;

    /**
     * 该类型座位的描述
     */
    private String description;

    /**
     * 与场馆计划一对一，场馆计划编号作为外键
     */
    @OneToOne
    @MapsId("venuePlanId")
    @JoinColumn(name = "venuePlanId", foreignKey = @ForeignKey(name = "FK_VENUE_PLAN"))
    private VenuePlan venuePlan;

    /**
     * 与场馆计划的座位一对多
     */
    @OneToMany(mappedBy = "seatType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VenuePlanSeat> venuePlanSeatList = new ArrayList<>();

    public SeatTypeId getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(SeatTypeId seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public VenuePlan getVenuePlan() {
        return venuePlan;
    }

    public void setVenuePlan(VenuePlan venuePlan) {
        this.venuePlan = venuePlan;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
