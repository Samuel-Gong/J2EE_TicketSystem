package edu.nju.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
