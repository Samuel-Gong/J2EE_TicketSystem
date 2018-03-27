package edu.nju.service.impl;

import edu.nju.dao.OrderDao;
import edu.nju.dao.VenueDao;
import edu.nju.dto.SeatCheckInDTO;
import edu.nju.dto.VenuePlanBriefDTO;
import edu.nju.dto.VenuePlanDetailDTO;
import edu.nju.model.*;
import edu.nju.service.VenueService;
import edu.nju.util.LocalDateTimeUtil;
import edu.nju.util.OrderStatus;
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
        //表示正在审批
        venue.setAuditing(true);
        return venue.getId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateVenue(Venue venue) {

        Venue oldVenue = venueDao.getVenue(venue.getId());
        assert oldVenue != null;

        List<VenueSeat> oldSeatMap = oldVenue.getSeatMap();

        //更改场馆的基本信息
        oldVenue.setName(venue.getName());
        oldVenue.setCity(venue.getCity());

        List<VenueSeat> newSeatMap = venue.getSeatMap();
        newSeatMap.forEach(venueSeat -> venueSeat.setVenue(venue));

        //场馆座位的行列数没有变化
        if (oldVenue.getRowNum() == venue.getRowNum() && oldVenue.getColumnNum() == venue.getColumnNum()) {
            //只更新是否位置是否有座位
            newSeatMap.forEach(newSeat -> {
                int index = oldVenue.getSeatMap().indexOf(newSeat);
                if (index != -1) {
                    oldSeatMap.get(index).setHasSeat(newSeat.isHasSeat());
                }
            });
        }
        //行列数有变化
        else {

            //删除所有座位
            venueDao.deleteSeatMap(oldVenue.getId());

            //更新venue的行和列
            oldVenue.setRowNum(venue.getRowNum());
            oldVenue.setColumnNum(venue.getColumnNum());

            //添加新的座位
            newSeatMap.forEach(newSeat -> {
                newSeat.setVenue(oldVenue);
                oldSeatMap.add(newSeat);
            });
        }

        //设置为接收审批
        oldVenue.setAuditing(true);
        return true;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean login(int venueId, String venuePassword) {
        Venue venue = venueDao.getVenue(venueId);
        //当场馆密码正确且没有在审批的时候才允许登录
        return venue.getPassword().equals(venuePassword) && !venue.isAuditing();
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

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<Venue> getAuditingVenues() {
        List<Venue> venueList = venueDao.getAuditingVenue();
        venueList.forEach(venue -> Hibernate.initialize(venue.getSeatMap()));
        return venueList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean auditPass(int venueId) {
        Venue venue = venueDao.getVenue(venueId);
        venue.setAuditing(false);
        return true;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean modifyCheck(int venueId) {
        List<VenuePlan> venuePlans = venueDao.getAllVenuePlan(venueId);
        return venuePlans.size() == 0 || allPlansPassOver(venuePlans);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void sendTickets() {
        final int sendTicketsWeek = 2;

        List<VenuePlan> needSendTickets = venueDao.getNeedSendTickets(sendTicketsWeek);

        for (int i = 0; i < needSendTickets.size(); i++) {
            VenuePlan venuePlan = needSendTickets.get(i);
            //设置为已配票
            venuePlan.setSendTickets(true);

            //找出所有该场馆计划下没有配票的已预订订单
            List<Order> seatUnsettledOrders = venuePlan.getOrders().stream()
                    .filter(order -> order.getOrderStatus() == OrderStatus.BOOKED && !order.isSeatSettled())
                    .collect(Collectors.toList());

            //找出所有该场馆计划下还可用的座位
            List<VenuePlanSeat> availableSeats = venuePlan.getVenuePlanSeats().stream()
                    .filter(VenuePlanSeat::isAvailable)
                    .collect(Collectors.toList());

            for (int j = 0; j < seatUnsettledOrders.size(); j++) {
                Order order = seatUnsettledOrders.get(i);
                //获取指定座位类型的座位
                List<VenuePlanSeat> specificTypeSeats = availableSeats.stream()
                        .filter(venuePlanSeat -> venuePlanSeat.getTypeChar() == order.getSeatType())
                        .collect(Collectors.toList());
                //剩余座位足够
                if (specificTypeSeats.size() >= order.getSeatNum()) {
                    for (int k = 0; k < order.getSeatNum(); k++) {
                        VenuePlanSeat availableSeat = specificTypeSeats.get(k);
                        availableSeat.setOrder(order);
                        availableSeat.setAvailable(false);

                        //从可用座位中移除掉
                        availableSeats.remove(availableSeat);
                    }
                    //订单的配票状态设置为已配票
                    order.setSeatSettled(true);
                } else {
                    //剩余座位不足，订单的状态设为已取消，退还会员票价
                    order.setSeatEnough(false);
                    order.setOrderStatus(OrderStatus.CANCELED);
                    Member member = order.getMemberFK();
                    Account account = member.getAccount();
                    //全额退款
                    account.setBalance(account.getBalance() + order.getPrice());
                }
            }
        }

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void checkCompleteVenuePlans() {

        List<VenuePlan> completePlan = venueDao.getCompleteVenuePlans();

        completePlan.forEach(venuePlan -> {
            //将每个场馆的状态设置为已完成
            venuePlan.setComplete(true);
            venuePlan.getOrders().stream()
                    .filter(order -> order.getOrderStatus() == OrderStatus.BOOKED)
                    .forEach(order -> {
                        //将场馆计划下的已注册订单的状态改为已消费订单，并给会员增加积分
                        order.setOrderStatus(OrderStatus.COMSUMPED);
                        Member member = order.getMemberFK();
                        member.setPoints(member.getPoints() + order.getPrice());
                    });
        });
    }

    /**
     * 检查场馆计划是否都已经完成
     *
     * @param venuePlans 场馆计划列表
     * @return 是否都已完成
     */
    private boolean allPlansPassOver(List<VenuePlan> venuePlans) {
        return venuePlans.stream()
                //所有计划都满足计划结束时间早于当前时间
                .allMatch(venuePlan -> venuePlan.getEndTime().isBefore(LocalDateTimeUtil.nowTillSecond()));
    }
}
