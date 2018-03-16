package edu.nju.dto;

import edu.nju.model.VenueSeat;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/16
 * <p>
 * 场馆的座位信息
 */
public class VenueSeatInfoDTO {

    /**
     * 场馆的
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
     * 每个座位的信息
     */
    private List<VenueSeat> seatMap;

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    public List<VenueSeat> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(List<VenueSeat> seatMap) {
        this.seatMap = seatMap;
    }

    @Override
    public String toString() {
        return "VenueSeatInfoDTO{" +
                "venueId=" + venueId +
                ", rowNum=" + rowNum +
                ", columnNum=" + columnNum +
                ", seatMap=" + seatMap +
                '}';
    }
}
