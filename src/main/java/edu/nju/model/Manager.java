package edu.nju.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Shenmiu
 * @date 2018/03/04
 */
@Entity
@Table(name = "manager")
public class Manager {

    @Id
    private Integer id;

    private String password;

    /**
     * 未结算收入
     */
    private int unsettleIncome;

    /**
     * 已结算收入
     */
    private int settleIncome;

    public Manager() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUnsettleIncome() {
        return unsettleIncome;
    }

    public void setUnsettleIncome(int balance) {
        this.unsettleIncome = balance;
    }

    public int getSettleIncome() {
        return settleIncome;
    }

    public void setSettleIncome(int settleIncome) {
        this.settleIncome = settleIncome;
    }
}
