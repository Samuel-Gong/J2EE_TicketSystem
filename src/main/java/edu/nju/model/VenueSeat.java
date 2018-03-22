package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Shenmiu
 * @date 2018/03/14
 * <p>
 * 只表示场馆座位的分布情况
 */
@Entity
@Table(name = "venue_seat")
@JSONType(ignores = "venue", orders = {"row", "column", "hasSeat"})
public class VenueSeat implements Serializable {

    /**
     * 行
     */
    @Id
    @Column(name = "`row`")
    private int row;

    /**
     * 列
     */
    @Id
    @Column(name = "`column`")
    private int column;

    /**
     * 与场馆多对一， 场馆编号作为主键一部分
     * 不序列化/反序列化场馆
     */
    @JSONField(serialize = false, deserialize = false)
    @Id
    @ManyToOne
    @JoinColumn(name = "venueId", foreignKey = @ForeignKey(name = "FK_VENUE"))
    private Venue venue;

    /**
     * 表示该行该列是否有座位
     */
    private boolean hasSeat;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
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

        if (row != venueSeat.row) {
            return false;
        }
        if (column != venueSeat.column) {
            return false;
        }
        return venue.equals(venueSeat.venue);
    }

    @Override
    public int hashCode() {
        int result = venue.hashCode();
        result = 31 * result + row;
        result = 31 * result + column;
        return result;
    }
}