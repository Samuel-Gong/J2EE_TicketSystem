package edu.nju.model.embeddable;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Shenmiu
 * @date 2018/03/15
 */
@Embeddable
public class SeatTypeId implements Serializable{

    /**
     * 场馆计划的编号
     */
    private int venuePlanId;

    /**
     * 表示座位类型的字符
     */
    private char typeChar;

    public int getVenuePlanId() {
        return venuePlanId;
    }

    public void setVenuePlanId(int venueId) {
        this.venuePlanId = venueId;
    }

    public char getTypeChar() {
        return typeChar;
    }

    public void setTypeChar(char seatChar) {
        this.typeChar = seatChar;
    }
}
