package edu.nju.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import edu.nju.model.VenuePlanSeat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * 会员下订单时的数据传输对象
 */
@JSONType(orders = {"mail", "venueId", "venuePlanId", "createTime", "orderPlanSeats"})
public class MemberOrderDTO {

    /**
     * 会员邮箱
     */
    private String mail;

    /**
     * 场馆编号
     */
    private Integer venueId;

    /**
     * 场馆计划编号
     */
    private Integer venuePlanId;

    /**
     * 订单创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 被订单选中的所有场馆计划的座位
     */
    private List<RowAndColumnDTO> orderPlanSeats;

    /**
     * 立即购买选择的座位类型
     */
    private Character seatType;

    /**
     * 立即购买选用的座位数量
     */
    private Integer seatNum;

    /**
     * 订单总价格
     */
    private Integer price;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getVenuePlanId() {
        return venuePlanId;
    }

    public void setVenuePlanId(Integer venuePlanId) {
        this.venuePlanId = venuePlanId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<RowAndColumnDTO> getOrderPlanSeats() {
        return orderPlanSeats;
    }

    public void setOrderPlanSeats(List<RowAndColumnDTO> orderPlanSeats) {
        this.orderPlanSeats = orderPlanSeats;
    }

    public Character getSeatType() {
        return seatType;
    }

    public void setSeatType(Character seatType) {
        this.seatType = seatType;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MemberOrderDTO{" +
                "mail='" + mail + '\'' +
                ", venueId=" + venueId +
                ", venuePlanId=" + venuePlanId +
                ", createTime=" + createTime +
                ", orderPlanSeats=" + orderPlanSeats +
                ", seatType=" + seatType +
                ", seatNum=" + seatNum +
                ", price=" + price +
                '}';
    }
}
