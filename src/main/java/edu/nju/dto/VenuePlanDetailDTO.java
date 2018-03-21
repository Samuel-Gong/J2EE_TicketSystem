package edu.nju.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import edu.nju.model.SeatType;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.model.VenuePlanSeat;
import edu.nju.util.ShowType;
import edu.nju.util.deserializer.ShowTypeDeserializer;
import edu.nju.util.serializer.ShowTypeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/21
 *
 * 演出详情数据传输对象:
 * 1. 座位行数
 * 2. 座位列数
 * 3. 场馆计划具体信息
 */
@JSONType(orders = {"venueId", "rowNum", "columnNum", "venuePlan"})
public class VenuePlanDetailDTO {

    /**
     * 场馆编号
     */
    private Integer venueId;

    /**
     * 座位行数
     */
    private Integer rowNum;

    /**
     * 座位列数
     */
    private Integer columnNum;

    /**
     * 场馆计划具体信息
     */
    private VenuePlan venuePlan;

    public VenuePlanDetailDTO(int venueId, int rowNum, int columnNum, VenuePlan venuePlan) {
        this.venueId = venueId;
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.venuePlan = venuePlan;
    }


    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public VenuePlan getVenuePlan() {
        return venuePlan;
    }

    public void setVenuePlan(VenuePlan venuePlan) {
        this.venuePlan = venuePlan;
    }

    @Override
    public String toString() {
        return "VenuePlanDetailDTO{" +
                "venueId=" + venueId +
                ", rowNum=" + rowNum +
                ", columnNum=" + columnNum +
                ", venuePlan=" + venuePlan +
                '}';
    }
}
