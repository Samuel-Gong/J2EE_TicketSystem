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
 *
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
    @Column(length = 7)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 座位分布
     */
    @OneToMany(mappedBy = "venue", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<VenueSeat> seatMap = new ArrayList<>();

    /**
     * 场馆计划
     */
    @JSONField(serialize = false, deserialize = false)
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VenuePlan> venuePlans = new ArrayList<>();

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

    public List<VenuePlan> getVenuePlans() {
        return venuePlans;
    }

    public void setVenuePlans(List<VenuePlan> venuePlans) {
        this.venuePlans = venuePlans;
    }
}
