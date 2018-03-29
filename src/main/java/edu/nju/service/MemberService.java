package edu.nju.service;

import edu.nju.dto.LevelAndDiscount;
import edu.nju.dto.PointsAndCoupons;
import edu.nju.model.Member;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 会员业务接口
 */
public interface MemberService {

    /**
     * 会员注册
     *
     * @param member 会员信息
     * @return 是否注册成功
     */
    boolean register(Member member);

    /**
     * 邮箱验证
     *
     * @param mail    邮箱
     * @param mailKey 邮箱密钥
     * @return 是否验证成功
     */
    boolean mailConfirm(String mail, int mailKey);

    /**
     * 登录
     *
     * @param mail     会员邮箱
     * @param password 密码
     * @return 是否登录成功
     */
    boolean logIn(String mail, String password);

    /**
     * 修改密码
     *
     * @param mail        会员邮箱
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return
     */
    boolean modifyPassword(String mail, String oldPassword, String newPassword);

    /**
     * 修改会员信息
     *
     * @param member 会员信息
     * @return 是否修改成功
     */
    boolean updateInfo(Member member);

    /**
     * 会员取消资格
     *
     * @param mail 会员邮箱
     * @return 是否取消成功
     */
    boolean disqualify(String mail);

    /**
     * 根据会员的邮箱
     *
     * @param mail 会员邮箱
     * @return 会员的等级以及折扣信息
     */
    LevelAndDiscount getLevelAndDiscount(String mail);

    /**
     * 根据会员id获取会员信息
     *
     * @param memberId 会员id
     * @return 会员信息
     */
    Member getInfo(String memberId);

    /**
     * 根据会员id获取会员积分及优惠券（面值与张数对应）
     *
     * @param memberId 会员id
     * @return
     */
    PointsAndCoupons getPointsAndCoupons(String memberId);

    /**
     * 检查会员是否有绑定的支付宝
     *
     * @param mail 会员邮箱
     * @return 是否有绑定
     */
    boolean checkAccount(String mail);

    /**
     * 会员绑定支付宝
     *
     * @param mail            会员邮箱
     * @param accountId       支付宝id
     * @param accountPassword 支付宝密码
     * @return 绑定是否成功
     */
    boolean bindAccount(String mail, int accountId, String accountPassword);
}
