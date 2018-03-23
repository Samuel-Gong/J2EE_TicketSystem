package edu.nju.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import edu.nju.model.Order;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * 订单展示的数据传输对象
 */
@JSONType(orders = {"order", "venuePlan"})
public class OrderShowDTO {

    /**
     * 订单的信息
     */
    @JSONField(deserialize = false)
    private Order order;

    /**
     * 场馆计划的简要信息
     */
    @JSONField(deserialize = false)
    private VenuePlanBriefDTO venuePlan;

    public OrderShowDTO(Order order, VenuePlanBriefDTO venuePlan) {
        this.order = order;
        this.venuePlan = venuePlan;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public VenuePlanBriefDTO getVenuePlan() {
        return venuePlan;
    }

    public void setVenuePlan(VenuePlanBriefDTO venuePlan) {
        this.venuePlan = venuePlan;
    }

    @Override
    public String toString() {
        return "OrderShowDTO{" +
                "order=" + order +
                ", venuePlan=" + venuePlan +
                '}';
    }
}
