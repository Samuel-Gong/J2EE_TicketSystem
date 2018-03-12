package edu.nju.dao;

import edu.nju.model.Member;

/**
 * @author Shenmiu
 * @date 2018/03/04
 *
 * 会员数据访问接口
 */
public interface MemberDao {

    /**
     * 查看会员资料
     * @param id 会员id
     * @return 会员对象
     */
    Member getMember(String id);

    /**
     * 添加一个会员
     * @param member 会员对象
     * @return 添加是否成功
     */
    boolean addMember(Member member);

    /**
     * 会员取消资格
     * @param id 会员id
     * @return 取消资格影响行数
     */
    int disqulify(String id);

    /**
     * 修改会员信息
     * @param member 会员资料
     * @return 修改是否成功
     */
    boolean updateInfo(Member member);


}
