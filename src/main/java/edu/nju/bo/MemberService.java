package edu.nju.bo;

import edu.nju.model.Member;

/**
 * @author Shenmiu
 * @date 2018/03/04
 *
 * 会员业务接口
 */
public interface MemberService {

    /**
     * 会员注册
     * @param member 会员信息
     * @return 是否注册成功
     */
    boolean register(Member member);

    /**
     * 邮箱验证
     * @return 是否验证成功
     */
    boolean mailConfirm(String mail);

    /**
     * 登录
     * @param id 会员id
     * @param password 密码
     * @return 是否登录成功
     */
    boolean logIn(String id, String password);

    /**
     * 修改会员信息
     * @param member 会员信息
     * @return 是否修改成功
     */
    boolean updateInfo(Member member);

}
