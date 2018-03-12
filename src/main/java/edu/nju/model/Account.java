package edu.nju.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Shenmiu
 * @date 2018/03/04
 *
 * 存钱账户
 */
@Entity
@Table(name = "account")
public class Account {

    /**
     * 账户的账号
     */
    @Id
    private String id;

    /**
     * 密码
     */
    private String password;

    /**
     * 余额
     */
    private double balance;

    public Account() {
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
