package edu.nju.model;

import edu.nju.model.embeddable.VenuePlanSeatId;

import javax.persistence.*;

/**
 * @author Shenmiu
 * @date 2018/03/15
 * <p>
 * 表示某个场馆计划座位的使用情况
 */
@Entity
@Table(name = "venue_plan_seat")
public class VenuePlanSeat {

    /**
     * 联合主键：
     * venuePlanId  场馆计划编号
     * row          该座位所在行
     * column       该座位所在列
     */
    @EmbeddedId
    private VenuePlanSeatId venuePlanSeatId;

    @ManyToOne
    @MapsId("venuePlanId")
    @JoinColumns({
            @JoinColumn(name = "venuePlanId", referencedColumnName = "venuePlanId"),
            @JoinColumn(name = "typeChar", referencedColumnName = "typeChar")
    })
    private SeatType seatType;

    @ManyToOne
    @MapsId("venuePlanId")
    @JoinColumn(name = "venuePlanId", foreignKey = @ForeignKey(name = "FK_VENUE_PLAN"))
    private VenuePlan venuePlan;

}
