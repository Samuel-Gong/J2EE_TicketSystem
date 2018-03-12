package edu.nju.model;


import javax.persistence.*;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 会员
 */
@NamedQueries({
        @NamedQuery(
                name = "get_member_by_mail",
                query = "from Member where mail = :mail"
        ),
        @NamedQuery(
                name = "disqulify",
                query = "update Member m set m.qualified = 0 where m.mail = :mail"
        ),
        @NamedQuery(
                name = "get_mailKey_by_mail",
                query = "select mailKey from Member where mail = :mail"
        )
})

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
     * 消费总额
     */
    private double totalComsumption;

    /**
     * 是否已经经过邮箱激活
     */
    private boolean confirmed;

    /**
     * 验证密钥
     */
    private int mailKey;

    public Member() {
    }

    public Member(String mail, String password) {
        this.mail = mail;
        this.password = password;

        this.qualified = true;
        this.points = 0;
        this.totalComsumption = 0;
        this.confirmed = false;
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

    public double getTotalComsumption() {
        return totalComsumption;
    }

    public void setTotalComsumption(double totalComsumption) {
        this.totalComsumption = totalComsumption;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean activated) {
        this.confirmed = activated;
    }

    public int getMailKey() {
        return mailKey;
    }

    public void setMailKey(int key) {
        this.mailKey = key;
    }
}
