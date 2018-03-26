package edu.nju.dao;

import edu.nju.dto.RowAndColumnDTO;
import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.model.VenuePlanSeat;
import edu.nju.model.VenueSeat;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/16
 * <p>
 * 场馆数据访问接口
 */
public interface VenueDao {

    //场馆

    /**
     * 添加一个场馆
     *
     * @param venue 场馆信息
     * @return 是否添加成功
     */
    boolean addVenue(Venue venue);

    /**
     * 获取场馆信息
     *
     * @param venueId 场馆编号
     * @return 场馆信息
     */
    Venue getVenue(int venueId);

    /**
     * 更新一个场馆
     *
     * @param venue 场馆信息
     */
    void updateVenue(Venue venue);

    /**
     * 根据场馆编号获取场馆的密码
     *
     * @param venueId 场馆编号
     * @return 场馆密码
     */
    String getPassword(int venueId);

    /**
     * 更新场馆基础信息
     *
     * @param venue 场馆信息
     * @return 是否更新成功
     */
    boolean updateBasicInfo(VenueBasicInfoDTO venue);

    /**
     * 在行列数没有变化的情况下，单纯更新每个位置是否有座位
     *
     * @param venueSeatInfoDTO 更新的场馆座位信息
     * @return 是否更新成功
     */
    boolean updateSeatMap(VenueSeatInfoDTO venueSeatInfoDTO);

    //场馆座位

    /**
     * 添加场馆座位信息
     *
     * @param seatMap 场馆座位列表
     */
    void addSeatMap(List<VenueSeat> seatMap);

    /**
     * 删除对应场馆编号的所有座位
     *
     * @param venueId 场馆编号
     */
    void deleteSeatMap(int venueId);

    //场馆计划

    /**
     * 添加场馆计划
     *
     * @param venuePlan 场馆计划信息
     */
    void addVenuePlan(VenuePlan venuePlan);

    /**
     * 获取场馆计划
     *
     * @param venuePlanId 场馆计划id
     * @return 场馆计划信息
     */
    VenuePlan getVenuePlan(int venuePlanId);

    /**
     * 获取该场馆所有的场馆计划，不含座位类型和座位表
     *
     * @param venueId 场馆编号
     * @return 所有的场馆计划列表
     */
    List<VenuePlan> getAllVenuePlan(int venueId);

    /**
     * 更新场馆计划
     *
     * @param venuePlan 场馆计划信息
     */
    void updateVenuePlan(VenuePlan venuePlan);

    /**
     * 删除场馆计划
     *
     * @param venuePlan 场馆计划
     */
    void deleteVenuePlan(VenuePlan venuePlan);

    /**
     * 获取所有即将到来的场馆计划总数
     *
     * @return 即将到来的所有场馆计划总数
     */
    int getComingVenuePlanTotalNum();

    /**
     * 获取所有即将到来场馆计划
     *
     * @return 指定大小的场馆计划列表
     */
    List<VenuePlan> getComingVenuePlans();

    /**
     * 通过场馆计划id及座位行列序列，获取指定的场馆计划座位
     *
     * @param venuePlanId    场馆计划id
     * @param orderPlanSeats 座位行列列表
     * @return 指定的场馆计划座位
     */
    List<VenuePlanSeat> getSpecificSeats(Integer venuePlanId, List<RowAndColumnDTO> orderPlanSeats);

    /**
     * 通过场馆计划id及座位的行、列，获取指定座位
     *
     * @param venuePlanId 场馆计划id
     * @param row         座位行数
     * @param column      座位列数
     * @return
     */
    VenuePlanSeat getVenuePlanSeat(int venuePlanId, int row, int column);

    /**
     * 获取正在审批的场馆
     * @return 审批场馆列表
     */
    List<Venue> getAuditingVenue();
}
