package edu.nju.dao;

import edu.nju.dto.RowAndColumnDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.model.VenuePlanSeat;

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
     *
     * @return 审批场馆列表
     */
    List<Venue> getAuditingVenue();

    /**
     * 获取已经结束但是状态还没设为结束的场馆计划
     *
     * @return 场馆计划列表
     */
    List<VenuePlan> getCompleteVenuePlans();

    /**
     * 获取应该配票的场馆
     * @param sendTicketsWeek 演出前多少周开始配票
     * @return 现在应该配票的场馆
     */
    List<VenuePlan> getNeedSendTickets(int sendTicketsWeek);

    /**
     * 获取所有已结束、未结算的场馆计划
     * @return 已结束、为结算场馆计划列表
     */
    List<VenuePlan> getUnsettleVenuePlans();

    /**
     * 获取所有场馆
     * @return 获取所有场馆
     */
    List<Venue> getVenues();
}
