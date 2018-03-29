package edu.nju.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 场馆
 * <p>
 * JSONType： 可以指定序列化的顺序
 */

@Entity
@Table(name = "venue")
@JSONType(orders = {"id", "name", "city", "password", "rowNum", "columnNum"})
public class Venue {

    /**
     * 7位场馆编号
     * <p>
     * 使用主键自增策略
     */
    @Id
    @Column(length = 7, updatable = false, nullable = false)
    @GeneratedValue(generator = "venue_id_generator")
    @SequenceGenerator(name = "venue_id_generator", sequenceName = "venue_id_sequence", initialValue = 1000000, allocationSize = 1)
    private int id;

    /**
     * 场馆名称
     */
    private String name;

    /**
     * 城市
     */
    private String city;

    /**
     * 密码
     */
    private String password;

    /**
     * 行数
     */
    private int rowNum;

    /**
     * 列数
     */
    private int columnNum;

    /**
     * 表示是否正在审批
     */
    private boolean auditing;

    /**
     * 总收入
     */
    private int income;

    /**
     * 座位分布
     */
    @OneToMany(mappedBy = "venue", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<VenueSeat> seatMap = new ArrayList<>();

    /**
     * 场馆计划，与venue没有级联关系
     */
    @JSONField(serialize = false, deserialize = false)
    @OneToMany(mappedBy = "venue", orphanRemoval = true)
    private List<VenuePlan> venuePlans = new ArrayList<>();

    /**
     * 与订单一对多
     */
    @JSONField(serialize = false, deserialize = false)
    @OneToMany(mappedBy = "venue")
    private List<Order> orders = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public List<VenueSeat> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(List<VenueSeat> seatMap) {
        this.seatMap = seatMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public boolean isAuditing() {
        return auditing;
    }

    public void setAuditing(boolean auditing) {
        this.auditing = auditing;
    }

    public List<VenuePlan> getVenuePlans() {
        return venuePlans;
    }

    public void setVenuePlans(List<VenuePlan> venuePlans) {
        this.venuePlans = venuePlans;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Venue venue = (Venue) o;

        return id == venue.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
