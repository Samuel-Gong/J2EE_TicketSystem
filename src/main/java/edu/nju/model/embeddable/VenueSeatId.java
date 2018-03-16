package edu.nju.model.embeddable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Shenmiu
 * @date 2018/03/15
 */
@Embeddable
@JSONType(orders = {"row", "column"})
public class VenueSeatId implements Serializable {

    /**
     * 行
     */
    @Column(name = "`row`")
    private int row;

    /**
     * 列
     */
    @Column(name = "`column`")
    private int column;

    /**
     * 场馆编号
     */
    @JSONField(serialize = false, deserialize = false)
    private int venueId;

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

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof VenueSeatId)) {
            return false;
        }

        final VenueSeatId venueSeatId = (VenueSeatId) obj;

        //只有当全部相等场馆编号、行、列全部相等的时候才返回true
        return venueSeatId.getRow() == getRow()
                && venueSeatId.getColumn() == getColumn()
                && venueSeatId.getVenueId() == getVenueId();

    }

    @Override
    public int hashCode() {
        int result = venueId;
        result = 29 * result + row;
        result = 29 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return "VenueSeatId{" +
                "row=" + row +
                ", column=" + column +
                ", venueId=" + venueId +
                '}';
    }
}
