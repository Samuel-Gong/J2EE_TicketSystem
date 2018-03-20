package edu.nju.service;

import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenuePlanBriefDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/15
 * <p>
 * 场馆业务逻辑接口
 */
public interface VenueService {

    //场馆

    /**
     * 查询场馆
     *
     * @param venueId 场馆编号
     * @return 场馆信息
     */
    Venue getVenue(int venueId);

    /**
     * 查询场馆，带上座位信息
     *
     * @param venueId 场馆编号
     * @return 带有座位信息的场馆
     */
    Venue getVenueWithSeatMap(int venueId);

    /**
     * 查询场馆，带上计划信息
     *
     * @param venueId 场馆编号
     * @return 带有场馆计划的场馆
     */
    Venue getVenueWithPlan(int venueId);

    /**
     * 场馆注册
     *
     * @param venue 场馆信息
     * @return 是否注册成功
     */
    boolean register(Venue venue);

    /**
     * 场馆登录
     *
     * @param venueId       场馆编号
     * @param venuePassword 场馆密码
     * @return 是否登录成功
     */
    boolean login(int venueId, String venuePassword);

    /**
     * 更新场馆基本信息
     *
     * @param venueBasicInfo 场馆信息
     * @return 是否修改成功
     */
    boolean updateBasicInfo(VenueBasicInfoDTO venueBasicInfo);

    //场馆座位

    /**
     * 更新场馆座位信息
     *
     * @param venueSeatInfo 场馆座位信息
     * @return 更新行数
     */
    boolean updateSeatMap(VenueSeatInfoDTO venueSeatInfo);

    //场馆计划

    /**
     * 添加一个场馆计划到指定编号的场馆里
     *
     * @param venueId   场馆编号
     * @param venuePlan 新场馆计划
     * @return 添加是否成功
     */
    boolean addVenuePlan(int venueId, VenuePlan venuePlan);

    /**
     * 获取场馆计划
     *
     * @param venuePlanId 场馆计划id
     * @return 场馆计划信息
     */
    VenuePlan getVenuePlan(int venuePlanId);

    /**
     * 获取场馆计划和该场馆计划对应的场馆
     *
     * @param venuePlanId 场馆计划编号
     * @return 带有场馆信息的场馆计划
     */
    VenuePlan getVenuePlanWithVenue(int venuePlanId);

    /**
     * 获取该场馆所有的场馆计划简介
     *
     * @param venueId 场馆编号
     * @return 场馆计划列表
     */
    List<VenuePlanBriefDTO> getAllBriefVenuePlan(int venueId);

    /**
     * 获取场馆计划详情，包括场馆计划的所有信息
     * @param venuePlanId 场馆计划Id
     * @return 场馆计划详情
     */
    VenuePlan getVenuePlanDetail(int venuePlanId);

    /**
     * 更新场馆计划
     *
     * @param venuePlan 场馆计划
     * @return 更新是否成功
     */
    boolean updateVenuePlan(VenuePlan venuePlan);

    /**
     * 删除场馆计划
     *
     * @param venuePlan 场馆计划
     * @return 删除是否成功
     */
    boolean deleteVenuePlan(VenuePlan venuePlan);

    /**
     * 获取当前时间往后的最近几场场馆计划简要信息
     *
     * @return 场馆计划传输对象
     */
    List<VenuePlanBriefDTO> getComingVenueBriefPlan();
}
