package edu.nju.service.impl;

import edu.nju.dao.OrderDao;
import edu.nju.dao.VenueDao;
import edu.nju.dto.*;
import edu.nju.model.*;
import edu.nju.service.VenueService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shenmiu
 * @date 2018/03/15
 * <p>
 * 场馆业务逻辑的实现类
 */
@Service("venueService")
@Transactional(rollbackFor = RuntimeException.class)
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueDao venueDao;

    @Autowired
    OrderDao orderDao;

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Venue getVenue(int venueId) {
        return venueDao.getVenue(venueId);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Venue getVenueWithSeatMap(int venueId) {
        Venue venue = venueDao.getVenue(venueId);
        //强制加载seatMap
        Hibernate.initialize(venue.getSeatMap());
        return venue;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Venue getVenueWithPlan(int venueId) {
        Venue venue = venueDao.getVenue(venueId);
        //强制加载venuePlans
        Hibernate.initialize(venue.getVenuePlans());
        return venue;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(Venue venue) {
//        venue和venueSeat建立关系
        for (VenueSeat venueSeat : venue.getSeatMap()) {
            venueSeat.setVenue(venue);
        }
        venueDao.addVenue(venue);
        return venue.getId();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean login(int venueId, String venuePassword) {
        return venueDao.getPassword(venueId).equals(venuePassword);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateBasicInfo(VenueBasicInfoDTO venueBasicInfo) {
        return venueDao.updateBasicInfo(venueBasicInfo);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateSeatMap(VenueSeatInfoDTO venueSeatInfo) {
        //todo 测试
        Venue venue = getVenueWithSeatMap(venueSeatInfo.getVenueId());
        assert venue != null;
        //venueSeat和venue建立联系
        for (VenueSeat venueSeat : venueSeatInfo.getSeatMap()) {
            venueSeat.setVenue(venue);
        }
        //场馆座位的行列数没有变化
        if (venue.getRowNum() == venueSeatInfo.getRowNum() && venue.getColumnNum() == venueSeatInfo.getColumnNum()) {
            //只更新座位上是否有座位
            venueDao.updateSeatMap(venueSeatInfo);
        } else {
            //先删除座位
            venueDao.deleteSeatMap(venue.getId());
            //更新venue的行和列
            venue.setRowNum(venueSeatInfo.getRowNum());
            venue.setColumnNum(venueSeatInfo.getColumnNum());
            venueDao.updateVenue(venue);
            //再添加座位
            venueDao.addSeatMap(venueSeatInfo.getSeatMap());
        }
        //行列数有变化
        return venueDao.updateSeatMap(venueSeatInfo);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean addVenuePlan(int venueId, VenuePlan venuePlan) {
        //场馆计划和场馆建立联系
        Venue venue = getVenue(venueId);
        venuePlan.setVenue(venue);

        //座位类型和场馆计划建立联系
        for (SeatType seatType : venuePlan.getSeatTypes()) {
            seatType.setVenuePlan(venuePlan);
        }
        //场馆计划座位与场馆计划建立联系
        for (VenuePlanSeat venuePlanSeat : venuePlan.getVenuePlanSeats()) {
            venuePlanSeat.setVenuePlan(venuePlan);
        }

        venueDao.addVenuePlan(venuePlan);

        return true;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public VenuePlan getVenuePlan(int venuePlanId) {
        return venueDao.getVenuePlan(venuePlanId);
    }

    @Override
    public VenuePlanDetailDTO getVenuePlanDetail(int venuePlanId) {
        VenuePlan venuePlan = venueDao.getVenuePlan(venuePlanId);
        Hibernate.initialize(venuePlan.getSeatTypes());
        Hibernate.initialize(venuePlan.getVenuePlanSeats());

        Venue venue = venuePlan.getVenue();
        int venueId = venue.getId();
        int rowNum = venue.getRowNum();
        int columnNum = venue.getColumnNum();

        return new VenuePlanDetailDTO(venueId, rowNum, columnNum, venuePlan);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<VenuePlanBriefDTO> getAllBriefVenuePlan(int venueId) {
        List<VenuePlan> venuePlans = venueDao.getAllVenuePlan(venueId);

        return venuePlans.stream()
                .map(VenuePlanBriefDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateVenuePlan(VenuePlan venuePlan) {
        venueDao.updateVenuePlan(venuePlan);
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean deleteVenuePlan(VenuePlan venuePlan) {
        venueDao.deleteVenuePlan(venuePlan);
        return true;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<VenuePlanBriefDTO> getComingVenueBriefPlan() {
        return venueDao.getComingVenuePlans()
                .stream()
                .map(VenuePlanBriefDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<Order> getVenuePlanOrders(int venuePlanId) {
        return orderDao.getOrdersByVenuePlanId(venuePlanId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean seatCheckIn(SeatCheckInDTO seatCheckInDTO) {
        int venuePlanId = seatCheckInDTO.getVenuePlanId();
        int row = seatCheckInDTO.getRowAndColumnDTO().getRow();
        int column = seatCheckInDTO.getRowAndColumnDTO().getColumn();
        VenuePlanSeat checkedInSeat = venueDao.getVenuePlanSeat(venuePlanId, row, column);
        checkedInSeat.setCheckIn(true);
        return true;
    }
}
