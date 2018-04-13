package edu.nju.dao;

import edu.nju.model.VenuePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/04/13
 */
public interface VenuePlanDao extends JpaRepository<VenuePlan, Integer> {

    /**
     * 添加或保存场馆计划
     *
     * @param venuePlan 场馆计划信息
     * @return 场馆信息
     */
    @Override
    <S extends VenuePlan> S save(S venuePlan);

    /**
     * 获取场馆计划
     *
     * @param venuePlanId 场馆计划id
     * @return 场馆计划信息
     */
    @Override
    VenuePlan getOne(Integer venuePlanId);

    /**
     * 获取该场馆所有的场馆计划，不含座位类型和座位表
     *
     * @param venueId 场馆编号
     * @return 所有的场馆计划列表
     */
    List<VenuePlan> getVenuePlansByVenueId(int venueId);

    /**
     * 获取所有即将到来场馆计划
     *
     * @param now 当前时间
     * @return 指定大小的场馆计划列表
     */
    @Query("from VenuePlan where begin > :timeNow")
    List<VenuePlan> getComingVenuePlans(@Param("timeNow") LocalDateTime now);

    /**
     * 获取已经结束但是状态还没设为结束的场馆计划
     *
     * @param now 当前时间
     * @return 场馆计划列表
     */
    List<VenuePlan> getVenuePlansByEndTimeBeforeAndCompleteIsFalse(LocalDateTime now);

    /**
     * 获取应该配票的场馆
     *
     * @param sendTicketTime 演出前多少周开始配票
     * @return 现在应该配票的场馆
     */
    List<VenuePlan> getVenuePlansByBeginBeforeAndSendTicketsIsFalse(LocalDateTime sendTicketTime);

    /**
     * 获取所有已结束、未结算的场馆计划
     *
     * @return 已结束、未结算场馆计划列表
     */
    List<VenuePlan> getVenuePlansByCompleteIsTrueAndSettleIsFalse();

    /**
     * 获取所有已结算的场馆计划
     *
     * @param venueId 场馆id
     * @return 该场馆已结算的场馆计划列表
     */
    List<VenuePlan> getVenuePlansByVenueIdAndSettleIsTrue(int venueId);

    /**
     * 获取指定场馆的即将到来的计划
     *
     * @param venueId 场馆id
     * @return 指定场馆即将到来计划列表
     */
    @Query("from VenuePlan where venue.id = :venueId and endTime > :timeNow")
    List<VenuePlan> getComingVenuePlans(int venueId);

    /**
     * 获取指定场馆未结算的计划
     *
     * @param venueId 场馆id
     * @return 指定场馆未结算的计划列表
     */
    @Query("from VenuePlan where venue.id = :venueId and complete = true and settle = false")
    List<VenuePlan> getUnsettlePlans(@Param("venueId") int venueId);


}
