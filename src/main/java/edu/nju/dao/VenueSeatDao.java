package edu.nju.dao;

import edu.nju.dto.RowAndColumnDTO;
import edu.nju.model.VenuePlanSeat;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/04/13
 */
public interface VenueSeatDao {

    /**
     * 通过场馆计划id及座位的行、列，获取指定座位
     *
     * @param venuePlanId 场馆计划id
     * @param row         座位行数
     * @param column      座位列数
     * @return 指定座位
     */
    VenuePlanSeat getVenuePlanSeat(int venuePlanId, int row, int column);

    /**
     * 通过场馆计划id及座位行列序列，获取指定的场馆计划座位
     *
     * @param venuePlanId    场馆计划id
     * @param orderPlanSeats 座位行列列表
     * @return 指定的场馆计划座位
     */
    List<VenuePlanSeat> getSpecificSeats(Integer venuePlanId, List<RowAndColumnDTO> orderPlanSeats);

    /**
     * 删除对应场馆编号的所有座位
     *
     * @param venueId 场馆编号
     */
    void deleteSeatMap(int venueId);

}
