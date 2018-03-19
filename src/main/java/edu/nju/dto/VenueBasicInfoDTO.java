package edu.nju.dto;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @author Shenmiu
 * @date 2018/03/16
 * <p>
 * 场馆的基本信息
 */
@JSONType(orders = {"venueId, name, city"})
public class VenueBasicInfoDTO {

    /**
     * 场馆的id
     */
    private Integer venueId;

    /**
     * 场馆的名称
     */
    private String name;

    /**
     * 场馆所在城市
     */
    private String city;

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
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

    @Override
    public String toString() {
        return "VenueBasicInfoDTO{" +
                "venueId=" + venueId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
