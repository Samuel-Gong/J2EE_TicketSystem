package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import edu.nju.model.embeddable.VenueSeatId;

import javax.persistence.*;

/**
 * @author Shenmiu
 * @date 2018/03/14
 * <p>
 * 只表示场馆座位的分布情况
 */
@Entity
@Table(name = "venue_seat")
@JSONType(orders = {"venueSeatId", "hasSeat"})
public class VenueSeat {

    /**
     * 联合主键：
     * row      行
     * column   列
     * venueId  场馆编号
     */
    @EmbeddedId
    private VenueSeatId venueSeatId;

    /**
     * 与场馆多对一
     * 不序列化/反序列化场馆
     */
    @JSONField(serialize = false, deserialize = false)
    @MapsId("venueId")
    @ManyToOne
    @JoinColumn(name = "venueId", foreignKey = @ForeignKey(name = "FK_VENUE"))
    private Venue venue;

    /**
     * 表示该行该列是否有座位
     */
    private boolean hasSeat;

    public VenueSeatId getVenueSeatId() {
        return venueSeatId;
    }

    public void setVenueSeatId(VenueSeatId venueSeatId) {
        this.venueSeatId = venueSeatId;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public boolean isHasSeat() {
        return hasSeat;
    }

    public void setHasSeat(boolean hasSeat) {
        this.hasSeat = hasSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VenueSeat venueSeat = (VenueSeat) o;

        return venueSeatId.equals(venueSeat.venueSeatId);
    }

    @Override
    public int hashCode() {
        return venueSeatId.hashCode();
    }
}
