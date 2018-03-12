package edu.nju.model;

import edu.nju.model.embedable.PlanPK;
import edu.nju.util.ShowType;

import javax.persistence.*;

/**
 * @author Shenmiu
 * @date 2018/03/04
 *
 * 场馆计划
 */
@Entity
@Table(name = "venue_plan")
public class VenuePlan {

    /**
     * 场馆编号和日期作为联合主键
     */
    @EmbeddedId
    private PlanPK planPK;

    /**
     * 场馆价格分布，json格式存储
     */
    @Column(name = "price_distribution")
    private String priceDistribution;

    /**
     * 演出类型
     */
    @Column(name = "show_type")
    @Enumerated(EnumType.STRING)
    private ShowType showType;

    /**
     * 简单描述
     */
    private String description;

}
