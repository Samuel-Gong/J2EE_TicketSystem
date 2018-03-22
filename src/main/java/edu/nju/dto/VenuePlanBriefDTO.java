package edu.nju.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import edu.nju.model.VenuePlan;
import edu.nju.util.ShowType;
import edu.nju.util.deserializer.ShowTypeDeserializer;
import edu.nju.util.serializer.ShowTypeSerializer;

import java.time.LocalDateTime;

/**
 * @author Shenmiu
 * @date 2018/03/19
 * <p>
 * 用于保存场馆计划的简介
 */
@JSONType(orders = {"venuePlanId", "name", "begin", "end", "showType", "description"})
public class VenuePlanBriefDTO {

    /**
     * 场馆计划的id
     */
    private Integer venuePlanId;

    /**
     * 场馆计划开始时间，每个计划的开始时间不可以相同
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime begin;

    /**
     * 场馆计划结束时间，每个计划的结束时间不可以相同
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private LocalDateTime end;

    /**
     * 演出类型
     */
    @JSONField(serializeUsing = ShowTypeSerializer.class, deserializeUsing = ShowTypeDeserializer.class)
    private ShowType showType;

    /**
     * 简单描述
     */
    private String description;

    public VenuePlanBriefDTO(VenuePlan venuePlan) {
        this.venuePlanId = venuePlan.getVenuePlanId();
        this.begin = venuePlan.getBegin();
        this.end = venuePlan.getEnd();
        this.showType = venuePlan.getShowType();
        this.description = venuePlan.getDescription();
    }

    public Integer getVenuePlanId() {
        return venuePlanId;
    }

    public void setVenuePlanId(Integer venuePlanId) {
        this.venuePlanId = venuePlanId;
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

    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VenuePlanBriefDTO{" +
                "venuePlanId=" + venuePlanId +
                ", begin=" + begin +
                ", end=" + end +
                ", showType=" + showType +
                ", description='" + description + '\'' +
                '}';
    }
}
