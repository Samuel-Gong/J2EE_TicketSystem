package edu.nju.dao;

import edu.nju.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * 会员数据访问接口
 */
public interface MemberDao extends JpaRepository<Member, String> {

    /**
     * 查看会员资料
     *
     * @param mail 会员邮箱
     * @return 会员对象
     */
    @Override
    Optional<Member> findById(String mail);

    /**
     * 查看会员资料
     *
     * @param mail 会员邮箱
     * @return 会员对象
     */
    @Override
    Member getOne(String mail);

    /**
     * 添加一个会员
     *
     * @param member 会员对象
     * @return 添加是否成功
     */
    @Override
    <S extends Member> S save(S member);

    /**
     * 获取所有会员分数
     *
     * @return 所有会员分数列表
     */
    @Query("select points from Member")
    List<Integer> getAllMembersPoints();
}
