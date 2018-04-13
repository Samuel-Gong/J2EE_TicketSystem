package edu.nju.dto;

import edu.nju.model.Venue;

/**
 * @author Shenmiu
 * @date 2018/03/31
 * <p>
 * 各场馆统计数据传输对象
 */
public class VenueStatisticsDTO {

    /**
     * 场馆对象
     */
    private Venue venue;

    /**
     * 已结算总收入
     */
    private Integer totalIncome;

    public VenueStatisticsDTO(Venue venue, int sum) {
        this.venue = venue;
        this.totalIncome = sum;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "VenueStatisticsDTO{" +
                "venue=" + venue +
                ", totalIncome=" + totalIncome +
                '}';
    }
}
