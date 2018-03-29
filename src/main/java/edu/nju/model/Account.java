package edu.nju.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 支付宝账户
 */
@Entity
@Table(name = "account")
public class Account {

    /**
     * 支付宝账号
     */
    @Id
    private int id;

    /**
     * 支付宝密码
     */
    private String password;

    /**
     * 余额
     */
    private double balance;

    /**
     * 和会员一对一
     */
    @OneToOne(mappedBy = "account")
    private Member member;

    public Account() {
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
