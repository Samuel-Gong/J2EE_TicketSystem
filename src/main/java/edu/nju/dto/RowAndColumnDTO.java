package edu.nju.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/**
 * @author Shenmiu
 * @date 2018/03/22
 *
 * 表示订单的座位：行和列
 */
@JSONType(orders = {"row", "column"})
public class RowAndColumnDTO {

    /**
     * 座位行
     */
    private Integer row;

    /**
     * 座位列
     */
    private Integer column;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "RowAndColumnDTO{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
