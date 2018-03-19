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
@JSONType(ignores = {"venue"}, orders = {"venuePlanId", "begin", "end", "showType", "description"})
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
    @Column(nullable = false, unique = true)
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime begin;

    /**
     * 场馆计划结束时间，每个计划的结束时间不可以相同
     */
    @Column(nullable = false, unique = true)
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime end;

    /**
     * 演出类型
     */
    @JSONField(serializeUsing = ShowTypeSerializer.class, deserializeUsing = ShowTypeDeserializer.class)
    @Column(name = "show_type")
    private ShowType showType;

    /**
     * 简单描述
     */
    private String description;

    /**
     * 与场馆多对一，外键为场馆编号
     */
    @ManyToOne
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

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
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
        if (!end.equals(venuePlan.end)) {
            return false;
        }
        return venue.equals(venuePlan.venue);
    }

    @Override
    public int hashCode() {
        int result = begin.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + venue.hashCode();
        return result;
    }
}
