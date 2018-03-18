package edu.nju.model.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 票的联合主键
 */
@Embeddable
public class TicketId implements Serializable {

    /**
     * 位置的行
     */
    @Column(name = "`row`")
    private int row;

    /**
     * 位置的列
     */
    @Column(name = "`column`")
    private int column;

    /**
     * 所属订单号
     */
    private int orderNum;

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

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
}
