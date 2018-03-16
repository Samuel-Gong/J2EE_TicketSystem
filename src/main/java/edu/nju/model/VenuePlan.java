package edu.nju.model;

import edu.nju.util.ShowType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Shenmiu
 * @date 2018/03/04
 *
 * 场馆计划
 */
@Entity
@Table(name = "venue_plan")
public class VenuePlan {

    /**
     * 场馆计划的id
     */
    @Id
    private int venuePlanId;

    /**
     * 场馆计划开始时间
     */
    private LocalDateTime begin;

    /**
     * 场馆计划结束时间
     */
    private LocalDateTime end;

    /**
     * 与场馆多对一，外键为场馆编号
     */
    @ManyToOne
    @JoinColumn(name = "venueId", foreignKey = @ForeignKey(name = "FK_VENUE"))
    private Venue venue;

    @OneToOne(mappedBy = "venuePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private SeatType seatType;

    /**
     * 演出类型
     */
    @Column(name = "show_type")
    @Enumerated(EnumType.STRING)
    private ShowType showType;

    /**
     * 简单描述
     */
    private String description;

}
