package edu.nju.model.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Shenmiu
 * @date 2018/03/15
 *
 * 某个场馆计划座位的id
 */
@Embeddable
public class VenuePlanSeatId implements Serializable{

    /**
     * 场馆计划编号
     */
    private int venuePlanId;

    /**
     * 该座位所在行
     */
    private int row;

    /**
     * 该座位所在列
     */
    @Column(name = "`column`")
    private int column;

    public int getVenuePlanId() {
        return venuePlanId;
    }

    public void setVenuePlanId(int venuePlanId) {
        this.venuePlanId = venuePlanId;
    }

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
}
