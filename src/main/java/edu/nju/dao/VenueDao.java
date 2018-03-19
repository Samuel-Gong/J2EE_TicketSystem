package edu.nju.dao;

import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenueSeat;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/16
 * <p>
 * 场馆数据访问接口
 */
public interface VenueDao {

    /**
     * 获取场馆信息
     *
     * @param venueId 场馆编号
     * @return 场馆信息
     */
    Venue getVenue(int venueId);

    /**
     * 根据场馆编号获取场馆的密码
     *
     * @param venueId 场馆编号
     * @return 场馆密码
     */
    String getPassword(int venueId);

    /**
     * 添加一个场馆
     *
     * @param venue 场馆信息
     * @return 是否添加成功
     */
    boolean addVenue(Venue venue);

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

    /**
     * 删除对应场馆编号的所有座位
     *
     * @param venueId 场馆编号
     */
    void deleteSeatMap(int venueId);

    /**
     * 添加场馆座位信息
     *
     * @param seatMap 场馆座位列表
     */
    void addSeatMap(List<VenueSeat> seatMap);

    /**
     * 更新场馆的信息
     *
     * @param venue 场馆信息
     */
    void updateVenue(Venue venue);
}
