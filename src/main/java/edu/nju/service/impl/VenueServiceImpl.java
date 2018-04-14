package edu.nju.service.impl;

import edu.nju.dao.OrderDao;
import edu.nju.dao.VenueDao;
import edu.nju.dao.VenuePlanDao;
import edu.nju.dto.*;
import edu.nju.model.*;
import edu.nju.service.VenueService;
import edu.nju.util.LocalDateTimeUtil;
import edu.nju.util.OrderStatus;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    private VenueDao venueDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private VenuePlanDao venuePlanDao;


    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Venue getVenue(int venueId) {
        return venueDao.findById(venueId).get();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Venue getVenueWithSeatMap(int venueId) {
        Venue venue = venueDao.findById(venueId).get();
        //强制加载seatMap
        Hibernate.initialize(venue.getSeatMap());
        return venue;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(Venue venue) {
//        venue和venueSeat建立关系
        for (VenueSeat venueSeat : venue.getSeatMap()) {
            venueSeat.setVenue(venue);
        }
        venueDao.save(venue);
        //表示正在审批
        venue.setAuditing(true);
        return venue.getId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateVenue(Venue venue) {

        Venue oldVenue = venueDao.findById(venue.getId()).get();
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
    public boolean login(Venue venue) {
        Optional<Venue> optionalVenue = venueDao.findById(venue.getId());
        //当场馆存在且场馆密码正确且没有在审批的时候才允许登录
        return optionalVenue.isPresent() && optionalVenue.get().getPassword().equals(venue.getPassword()) && !venue.isAuditing();
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

        venuePlan.getVenuePlanSeats().forEach(venuePlanSeat ->
                {
                    //如果场馆计划座位表示字符为_，说明也不可用
                    if (venuePlanSeat.getTypeChar() == '_') {
                        venuePlanSeat.setAvailable(false);
                    }
                    //场馆计划座位与场馆计划建立联系
                    venuePlanSeat.setVenuePlan(venuePlan);
                }
        );

        venuePlanDao.save(venuePlan);
        return true;
    }

    @Override
    public VenuePlanDetailDTO getVenuePlanDetail(int venuePlanId) {
        VenuePlan venuePlan = venuePlanDao.findById(venuePlanId).get();
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
    public List<VenuePlanBriefDTO> getComingVenueBriefPlan() {
        return venuePlanDao.getComingVenuePlans(LocalDateTimeUtil.nowTillMinute())
                .stream()
                .map(VenuePlanBriefDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean seatCheckIn(SeatCheckInDTO venuePlanSeat) {
        int venuePlanId = venuePlanSeat.getVenuePlanId();
        int row = venuePlanSeat.getRowAndColumnDTO().getRow();
        int column = venuePlanSeat.getRowAndColumnDTO().getColumn();
        VenuePlanSeat checkedInSeat = venueDao.getVenuePlanSeat(venuePlanId, row, column);

        //如果座位可用（表示该座位未被预订，则不能被登记）
        if (checkedInSeat.isAvailable()) {
            return false;
        }

        checkedInSeat.setCheckIn(true);
        return true;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<Venue> getAuditingVenues() {
        List<Venue> venueList = venueDao.getVenuesByAuditingIsTrue();
        venueList.forEach(venue -> Hibernate.initialize(venue.getSeatMap()));
        return venueList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean auditPass(int venueId) {
        Venue venue = venueDao.findById(venueId).get();
        venue.setAuditing(false);
        return true;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean modifyCheck(int venueId) {
        List<VenuePlan> venuePlans = venuePlanDao.getVenuePlansByVenueId(venueId);
        return venuePlans.size() == 0 || allPlansPassOver(venuePlans);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void sendTickets() {
        final int sendTicketsWeek = 2;

        List<VenuePlan> needSendTickets = venuePlanDao.getVenuePlansByBeginBeforeAndSendTicketsIsFalse(LocalDateTimeUtil.nowTillMinute().plusWeeks(2));

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
                    .filter(venuePlanSeat -> venuePlanSeat.isAvailable() && venuePlanSeat.getTypeChar() != '_')
                    .collect(Collectors.toList());

            for (int j = 0; j < seatUnsettledOrders.size(); j++) {
                Order order = seatUnsettledOrders.get(j);
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
                    Member member = order.getMemberFk();
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

        List<VenuePlan> completePlan = venuePlanDao.getVenuePlansByEndTimeBeforeAndCompleteIsFalse(LocalDateTimeUtil.nowTillMinute());

        completePlan.forEach(venuePlan -> {
            //将每个场馆计划的状态设置为已完成
            venuePlan.setComplete(true);
            venuePlan.getOrders().stream()
                    .filter(order -> order.getOrderStatus() == OrderStatus.BOOKED)
                    .forEach(order -> {
                        //将场馆计划下的已注册订单的状态改为已消费订单，并给会员增加积分
                        order.setOrderStatus(OrderStatus.CONSUMED);
                        //如果是会员订单则给会员增加积分，否则不增加
                        if (order.isMemberOrder()) {
                            Member member = order.getMemberFk();
                            member.setPoints(member.getPoints() + order.getActualPrice());
                            //会员总消费也增加
                            member.setTotalConsumption(member.getTotalConsumption() + order.getActualPrice());
                        }

                        //累加该计划的票价总收入
                        venuePlan.setTotalIncome(venuePlan.getTotalIncome() + order.getActualPrice());
                    });
        });
    }

    @Override
    public List<VenueAndPlanDTO> getUnsettleVenuePlans() {
        return venuePlanDao.getVenuePlansByCompleteIsTrueAndSettleIsFalse().stream()
                .map(venuePlan -> new VenueAndPlanDTO(venuePlan, venuePlan.getVenue()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public VenueFinanceDTO getFinance(int venueId) {
        Venue venue = venueDao.findById(venueId).get();

        List<Order> orders = venue.getOrders();

        //总预订票价
        int totalBooked = 0;
        //总退订票价
        int totalRefund = 0;

        for (Order order : orders) {
            switch (order.getOrderStatus()) {
                case BOOKED:
                    totalBooked += order.getActualPrice();
                    break;
                case RETREAT:
                    totalRefund += order.getActualPrice();
                    break;
                default:
                    break;
            }
        }

        List<VenuePlan> venuePlans = venue.getVenuePlans();

        //未结算
        int totalUnsettle = 0;
        //已结算
        int totalSettle = 0;

        for (VenuePlan venuePlan : venuePlans) {
            if (venuePlan.isSettle()) {
                totalSettle += venuePlan.getActualIncome();
            } else {
                totalUnsettle += venuePlan.getTotalIncome();
            }
        }

        return new VenueFinanceDTO(totalBooked, totalRefund, totalSettle, totalUnsettle);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<VenueStatisticsDTO> getVenueStatistics() {
        List<Venue> venues = venueDao.findAll();
        return venues.stream()
                .map(venue ->
                        new VenueStatisticsDTO(venue, venue.getVenuePlans().stream()
                                .filter(VenuePlan::isSettle)
                                .mapToInt(VenuePlan::getActualIncome)
                                .sum()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<VenuePlanBriefDTO> getComingVenuePlans(int venueId) {
        List<VenuePlan> venuePlans = venuePlanDao.getComingVenuePlans(venueId, LocalDateTimeUtil.nowTillMinute());
        return venuePlan2BriefDTO(venuePlans);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<VenuePlanBriefDTO> getSettlePlans(int venueId) {
        List<VenuePlan> venuePlans = venuePlanDao.getVenuePlansByVenueIdAndSettleIsTrue(venueId);
        return venuePlan2BriefDTO(venuePlans);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<VenuePlanBriefDTO> getUnsettlePlans(int venueId) {
        List<VenuePlan> venuePlans = venuePlanDao.getUnsettlePlans(venueId);
        return venuePlan2BriefDTO(venuePlans);
    }

    private List<VenuePlanBriefDTO> venuePlan2BriefDTO(List<VenuePlan> venuePlans) {
        return venuePlans.stream()
                .map(VenuePlanBriefDTO::new)
                .collect(Collectors.toList());
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
                .allMatch(venuePlan -> venuePlan.getEndTime().isBefore(LocalDateTimeUtil.nowTillMinute()));
    }
}
