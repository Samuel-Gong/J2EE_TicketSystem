package edu.nju.model.embedable;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 场馆计划主键
 */
@Embeddable
public class PlanPK implements Serializable {

    /**
     * 7位场馆号
     */
    @Column(length = 7)
    private String venueID;

    /**
     * 场馆计划开始时间
     */
    private LocalDateTime begin;

    private LocalDateTime end;

    public PlanPK() {
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
