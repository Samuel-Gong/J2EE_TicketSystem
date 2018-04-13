package edu.nju.service.impl;

import edu.nju.dao.*;
import edu.nju.dto.OrderShowDTO;
import edu.nju.dto.RefundTipDTO;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.dto.VenuePlanBriefDTO;
import edu.nju.model.*;
import edu.nju.service.OrderService;
import edu.nju.service.strategy.RetreatStrategy;
import edu.nju.util.OrderStatus;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shenmiu
 * @date 2018/03/22
 * <p>
 * 订单接口实现
 */
@Service("orderService")
@Transactional(rollbackFor = RuntimeException.class)
public class OrderServiceImpl implements OrderService {

    private static int MANAGER_ID = 1;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private VenueDao venueDao;
    @Autowired
    private VenuePlanDao venuePlanDao;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private CouponDao couponDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int addOrder(TakeOrderDTO takeOrderDTO) {
        Venue venue = venueDao.getOne(takeOrderDTO.getVenueId());
        VenuePlan venuePlan = venuePlanDao.getOne(takeOrderDTO.getVenuePlanId());

        Order order = new Order(takeOrderDTO);

        //若是线上购买，设置状态为未支付
        if (order.isBoughtOnline()) {
            order.setOrderStatus(OrderStatus.UNPAID);
        }
        //现场购买，设置状态为已支付，并将收入转入到经理账户
        else {
            order.setOrderStatus(OrderStatus.BOOKED);
            //增加经理的未结算收入
            Manager manager = managerDao.getOne(MANAGER_ID);
            manager.setUnsettleIncome(manager.getUnsettleIncome() + order.getActualPrice());
        }

        //如果是会员购票
        if (order.isMemberOrder()) {
            //设置与会员的关联
            Member member = memberDao.getOne(takeOrderDTO.getMail());
            order.setMemberFk(member);
        }

        //如果是优惠券购票，找出一张指定面额的优惠券
        if (order.isUseCoupon()) {
            List<Coupon> coupons = couponDao.getUnusedCoupons(takeOrderDTO.getMail(), takeOrderDTO.getCouponValue());
            assert coupons.size() != 0;
            Coupon coupon = coupons.get(0);
            coupon.setUsed(true);

            order.setUseCoupon(true);
            order.setCoupon(coupon);
        }

        order.setVenue(venue);
        order.setVenuePlan(venuePlan);

        //如果是选定座位购票
        if (takeOrderDTO.getSeatSettled()) {
            //得到persistent状态的场馆计划座位，更新available状态，设置联系order的外键
            List<VenuePlanSeat> selectedVenuePlanSeats = venueDao.getSpecificSeats(takeOrderDTO.getVenuePlanId(), takeOrderDTO.getOrderSeats());
            selectedVenuePlanSeats.forEach(seat -> {
                seat.setAvailable(false);
                seat.setOrder(order);
            });

            order.setVenuePlanSeats(selectedVenuePlanSeats);
        }
        //立即购票
        else {
            order.setSeatType(takeOrderDTO.getSeatType());
            order.setSeatNum(takeOrderDTO.getSeatNum());
        }

        //添加一条订单
        orderDao.save(order);

        return order.getOrderId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<OrderShowDTO> getOrderShowDTOs(String mail, OrderStatus orderStatus) {
        List<Order> orders = orderDao.getOrdersByMemberFkMailAndOrderStatusAndBoughtOnlineIsTrue(mail, orderStatus);
        //懒加载每个Order对应选择的座位
        orders.forEach(unpaidOrder -> Hibernate.initialize(unpaidOrder.getVenuePlanSeats()));
        return orders.stream()
                .map(unpaidOrder -> new OrderShowDTO(unpaidOrder, new VenuePlanBriefDTO(unpaidOrder.getVenuePlan())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public OrderShowDTO getOrderShowDTO(int orderId) {
        Order unpaidOrder = orderDao.getOne(orderId);
        Hibernate.initialize(unpaidOrder.getVenuePlanSeats());
        return new OrderShowDTO(unpaidOrder, new VenuePlanBriefDTO(unpaidOrder.getVenuePlan()));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean payOrder(String mail, int orderId) {
        Member member = memberDao.getOne(mail);
        Account account = member.getAccount();
        Order order = orderDao.getOne(orderId);

        //订单支付的时候再次检查，是否已经过了支付时间
        if (passOverPayTime(order.getCreateTime())) {
            return false;
        }

        //多于优惠价格即可支付
        if (account.getBalance() >= order.getActualPrice()) {
            //减少账户余额
            account.setBalance(account.getBalance() - order.getActualPrice());

            //将收入转入经理账户
            Manager manager = managerDao.getOne(MANAGER_ID);
            manager.setUnsettleIncome(manager.getUnsettleIncome() + order.getActualPrice());

            //改变订单状态为已预订
            assert OrderStatus.UNPAID == order.getOrderStatus();
            order.setOrderStatus(OrderStatus.BOOKED);

            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean cancelOrder(int orderId) {
        Order order = orderDao.getOne(orderId);
        //改变订单状态
        order.setOrderStatus(OrderStatus.CANCELED);

        setBookedSeatStr(order);
        detachSeat(order);
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RefundTipDTO getRetreatOrderTip(String mail, int orderId) {

        Order order = orderDao.getOne(orderId);

        //计算退还票价，给账户余额加上退还的票价
        return RetreatStrategy.calculateRefund(order.getVenuePlan().getBegin(), order.getActualPrice());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean refund(String mail, int orderId, int refund) {

        Order order = orderDao.getOne(orderId);
        Member member = memberDao.getOne(mail);
        Account account = member.getAccount();

        //给订单记录退还金额
        order.setRefund(refund);
        //给账户退还钱
        account.setBalance(account.getBalance() + refund);

        //扣除经理账户余额
        Manager manager = managerDao.getOne(MANAGER_ID);
        assert manager.getUnsettleIncome() >= order.getActualPrice();
        manager.setUnsettleIncome(manager.getUnsettleIncome() - order.getActualPrice());

        //改变订单状态
        order.setOrderStatus(OrderStatus.RETREAT);

        setBookedSeatStr(order);
        detachSeat(order);

        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void checkUnpaidOrders() {
        List<Order> unpaidOrders = orderDao.getOrdersByOrderStatus(OrderStatus.UNPAID);
        unpaidOrders.stream()
                //筛选出那些超过支付时间的订单
                .filter(unpaidOrder -> passOverPayTime(unpaidOrder.getCreateTime()))
                //取消超过支付时间的订单
                .forEach(unpaidOrder -> {
                    unpaidOrder.setOrderStatus(OrderStatus.CANCELED);
                    setBookedSeatStr(unpaidOrder);
                    detachSeat(unpaidOrder);
                });
    }

    /**
     * 过期、取消、退订订单需要与座位解绑，并将座位设置为可用
     */
    private void detachSeat(Order order) {
        //改变座位状态为可预定，无绑定订单
        order.getVenuePlanSeats().forEach(seat -> {
            seat.setAvailable(true);
            seat.setOrder(null);
        });
    }

    /**
     * 过期、取消、退订订单需要与座位解绑前，将订单预订的座位用字符串的形式保存下来
     */
    private void setBookedSeatStr(Order order) {
        String bookedSeatStr = order.getVenuePlanSeats().stream()
                .map(seat -> seat.getRow() + "排" + seat.getColumn() + "座")
                .collect(Collectors.joining(","));
        order.setBookedSeatStr(bookedSeatStr);
    }

    /**
     * 订单创建时间+15分钟在当前时间之前，则说明会员未在有效时间内支付订单
     *
     * @param createTime 订单创建时间
     * @return 订单是否超过支付时间
     */
    private boolean passOverPayTime(LocalDateTime createTime) {
        final int expireTime = 15;
        return createTime.plusMinutes(expireTime).isBefore(LocalDateTime.now());
    }
}
