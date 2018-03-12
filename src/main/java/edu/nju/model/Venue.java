package edu.nju.model;

import edu.nju.model.embedable.Address;

import javax.persistence.*;

/**
 * @author Shenmiu
 * @date 2018/03/04
 */
@Entity
@Table(name = "venue")
public class Venue {

    /**
     * 7位场馆编号
     */
    @Id
    @Column(length = 7)
    private String id;

    /**
     * 密码
     */
    private String password;

    /**
     * 地址
     */
    @Embedded
    private Address address;

    /**
     * 座位分布，json存储
     */
    @Column(name = "seat_distribution")
    private String seatDistribution;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSeatDistribution() {
        return seatDistribution;
    }

    public void setSeatDistribution(String seatDistribution) {
        this.seatDistribution = seatDistribution;
    }
}
