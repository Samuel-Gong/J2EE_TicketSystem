package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Shenmiu
 * @date 2018/03/15
 * <p>
 * 表示某个场馆计划的座位类型
 */
@Entity
@Table(name = "seat_type")
@JSONType(ignores = {"venuePlan"}, orders = {"typeChar", "price", "description"})
public class SeatType implements Serializable {

    /**
     * 与场馆计划一对一，场馆计划编号作为外键，也作为主键的一部分
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "venuePlanId", foreignKey = @ForeignKey(name = "FK_VENUE_PLAN"))
    private VenuePlan venuePlan;

    @Id
    private char typeChar;

    /**
     * 该类型座位的价格
     */
    private int price;

    /**
     * 该类型座位的描述
     */
    private String description;

    public VenuePlan getVenuePlan() {
        return venuePlan;
    }

    public void setVenuePlan(VenuePlan venuePlan) {
        this.venuePlan = venuePlan;
    }

    public char getTypeChar() {
        return typeChar;
    }

    public void setTypeChar(char typeChar) {
        this.typeChar = typeChar;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeatType seatType = (SeatType) o;

        if (typeChar != seatType.typeChar) {
            return false;
        }
        return venuePlan.equals(seatType.venuePlan);
    }

    @Override
    public int hashCode() {
        int result = venuePlan.hashCode();
        result = 31 * result + (int) typeChar;
        return result;
    }
}
