package edu.nju.model;


import javax.persistence.*;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 会员
 */
@Entity
@Table(name = "member")
public class Member {

    /**
     * 邮箱
     */
    @Id
    private String mail;

    /**
     * 密码
     */
    private String password;

    /**
     * 资格
     */
    private boolean qualified;

    /**
     * 积分
     */
    private int points;

    /**
     * 验证密钥
     */
    private int mailKey;

    /**
     * 和账户一对一
     */
    @OneToOne
    @JoinColumn(name = "accountId", foreignKey = @ForeignKey(name = "FK_ACCOUNT"))
    private Account account;

    /**
     * 会员的订单
     */
    @OneToMany(mappedBy = "memberFK")
    private List<Order> orders;

    /**
     * 会员的优惠券
     */
    @OneToMany(mappedBy = "memberFK")
    private List<Coupon> coupons;

    public Member() {
    }

    public Member(String mail, String password) {
        this.mail = mail;
        this.password = password;

        this.qualified = true;
        this.points = 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMailKey() {
        return mailKey;
    }

    public void setMailKey(int key) {
        this.mailKey = key;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orderList) {
        this.orders = orderList;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
