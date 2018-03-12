package edu.nju.model.embedable;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Shenmiu
 * @date 2018/03/04
 */
@Embeddable
public class Address implements Serializable{

    /**
     * 城市
     */
    private String city;

    /**
     * 县城
     */
    private String county;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
