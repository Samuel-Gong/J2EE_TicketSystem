package edu.nju.dao;

import edu.nju.model.Member;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 会员数据访问接口
 */
public interface MemberDao {

    /**
     * 查看会员资料
     *
     * @param mail 会员邮箱
     * @return 会员对象
     */
    Member getMember(String mail);

    /**
     * 添加一个会员
     *
     * @param member 会员对象
     * @return 添加是否成功
     */
    boolean addMember(Member member);

    /**
     * 会员取消资格
     *
     * @param mail 会员邮箱
     * @return 取消资格影响行数
     */
    int disqulify(String mail);

    /**
     * 修改会员信息
     *
     * @param member 会员资料
     * @return 修改是否成功
     */
    boolean updateMember(Member member);


    /**
     * 获取会员的邮箱密钥
     *
     * @param mail 会员邮箱
     * @return 邮箱密钥
     */
    int getMailKey(String mail);

    /**
     * 获取所有会员分数
     * @return 获取
     */
    List<Integer> getAllMembersPoints();
}
