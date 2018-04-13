package edu.nju.dao;

import edu.nju.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/16
 * <p>
 * 场馆数据访问接口
 */
public interface VenueDao extends JpaRepository<Venue, Integer>, VenueSeatDao {

    /**
     * 添加一个场馆
     *
     * @param venue 场馆信息
     * @return 是否添加成功
     */
    @Override
    <S extends Venue> S save(S venue);

    /**
     * 获取场馆信息
     *
     * @param venueId 场馆编号
     * @return 场馆信息
     */
    Venue getOne(int venueId);

    /**
     * 获取所有场馆
     *
     * @return 获取所有场馆
     */
    @Override
    List<Venue> findAll();

    /**
     * 获取正在审批的场馆
     *
     * @return 审批场馆列表
     */
    List<Venue> getVenuesByAuditingIsTrue();
}
