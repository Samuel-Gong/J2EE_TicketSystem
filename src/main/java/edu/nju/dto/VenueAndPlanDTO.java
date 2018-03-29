package edu.nju.dto;

import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;

/**
 * @author Shenmiu
 * @date 2018/03/29
 * <p>
 * 场馆和场馆计划的数据传输对象
 */
public class VenueAndPlanDTO {

    /**
     * 场馆
     */
    private Venue venue;

    /**
     * 场馆计划
     */
    private VenuePlan venuePlan;

    public VenueAndPlanDTO(VenuePlan venuePlan, Venue venue) {
        this.venue = venue;
        this.venuePlan = venuePlan;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public VenuePlan getVenuePlan() {
        return venuePlan;
    }

    public void setVenuePlan(VenuePlan venuePlan) {
        this.venuePlan = venuePlan;
    }

    @Override
    public String toString() {
        return "VenueAndPlanDTO{" +
                "venue=" + venue +
                ", venuePlan=" + venuePlan +
                '}';
    }
}
