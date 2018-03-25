package edu.nju.service.impl;

import edu.nju.dao.MemberDao;
import edu.nju.dao.OrderDao;
import edu.nju.dao.VenueDao;
import edu.nju.dto.OrderShowDTO;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.dto.VenuePlanBriefDTO;
import edu.nju.model.*;
import edu.nju.service.OrderService;
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
 * @date 2018/03/22
 * <p>
 * 订单接口实现
 */
@Service("orderService")
@Transactional(rollbackFor = RuntimeException.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private VenueDao venueDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int addPickSeatOrder(TakeOrderDTO takeOrderDTO) {
        //todo 任务调度
        Member member = memberDao.getMember(takeOrderDTO.getMail());
        Venue venue = venueDao.getVenue(takeOrderDTO.getVenueId());
        VenuePlan venuePlan = venueDao.getVenuePlan(takeOrderDTO.getVenuePlanId());

        Order order = new Order();
        //设定为当前时间
        order.setCreateTime(LocalDateTimeUtil.now());
        //设置订单总价
        order.setPrice(takeOrderDTO.getPrice());
        //订单状态设置为未支付
        order.setOrderStatus(OrderStatus.UNPAID);
        //订单是自选座位
        order.setSeatSettled(true);
        //订单是否是现场购票
        order.setBoughtOnSite(false);

        order.setMemberFK(member);
        order.setVenue(venue);
        order.setVenuePlan(venuePlan);

        //得到persistent状态的场馆计划座位，更新available状态，设置联系order的外键
        List<VenuePlanSeat> selectedVenuePlanSeats = venueDao.getSpecificSeats(takeOrderDTO.getVenuePlanId(), takeOrderDTO.getOrderPlanSeats());
        selectedVenuePlanSeats.forEach(seat -> {
            seat.setAvailable(false);
            seat.setOrder(order);
        });

        order.setVenuePlanSeats(selectedVenuePlanSeats);

        //添加一条订单
        orderDao.addOrder(order);

        //任务调度
//        jobService.addCheckUnpaidOrderJob(order.getCreateTime(), order.getOrderId());

        //更新会员及场馆信息
        member.getOrders().add(order);
        venue.getOrders().add(order);

        return order.getOrderId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int addBuyNowOrder(TakeOrderDTO takeOrderDTO) {
        //todo 任务调度
        Member member = memberDao.getMember(takeOrderDTO.getMail());
        Venue venue = venueDao.getVenue(takeOrderDTO.getVenueId());
        VenuePlan venuePlan = venueDao.getVenuePlan(takeOrderDTO.getVenuePlanId());

        Order order = new Order();
        //设定为当前时间
        order.setCreateTime(takeOrderDTO.getCreateTime());
        //设置订单总价
        order.setPrice(takeOrderDTO.getPrice());
        //订单状态设置为未支付
        order.setOrderStatus(OrderStatus.UNPAID);
        //订单不是自选座位，等待配票，座位未固定
        order.setSeatSettled(false);
        //订单是否是现场购票
        order.setBoughtOnSite(false);

        order.setMemberFK(member);
        order.setVenue(venue);
        order.setVenuePlan(venuePlan);

        //添加一条订单
        orderDao.addOrder(order);

        //更新会员及场馆信息
        member.getOrders().add(order);
        venue.getOrders().add(order);

        return order.getOrderId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<OrderShowDTO> getOrderShowDTOs(String mail, OrderStatus orderStatus) {
        List<Order> unpaidOrders = orderDao.getOrders(mail, orderStatus);
        //懒加载每个Order对应选择的座位
        unpaidOrders.forEach(unpaidOrder -> Hibernate.initialize(unpaidOrder.getVenuePlanSeats()));
        return unpaidOrders.stream()
                .map(unpaidOrder -> new OrderShowDTO(unpaidOrder, new VenuePlanBriefDTO(unpaidOrder.getVenuePlan())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void checkUnpaidOrder(int orderId) {
        Order order = orderDao.getOrder(orderId);
        if (OrderStatus.UNPAID == order.getOrderStatus()) {
            order.setOrderStatus(OrderStatus.EXPIRED);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public OrderShowDTO getOrderShowDTO(int orderId) {
        Order unpaidOrder = orderDao.getOrder(orderId);
        Hibernate.initialize(unpaidOrder.getVenuePlanSeats());
        return new OrderShowDTO(unpaidOrder, new VenuePlanBriefDTO(unpaidOrder.getVenuePlan()));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean payOrder(String mail, int orderId) {
        Member member = memberDao.getMember(mail);
        Account account = member.getAccount();
        Order order = orderDao.getOrder(orderId);
        //减少账户余额
        if (account.getBalance() >= order.getPrice()) {

            account.setBalance(account.getBalance() - order.getPrice());
            //改变订单状态为已预订
            assert OrderStatus.UNPAID == order.getOrderStatus();
            order.setOrderStatus(OrderStatus.BOOKED);

            member.setPoints(member.getPoints() + order.getPrice());

            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean cancelOrder(int orderId) {
        Order order = orderDao.getOrder(orderId);
        //改变订单状态
        order.setOrderStatus(OrderStatus.CANCELED);

        //将订单预订的座位用字符串的形式保存下来
        String bookedSeatStr = order.getVenuePlanSeats().stream()
                .map(seat -> seat.getRow() + "排" + seat.getColumn() + "座")
                .collect(Collectors.joining(","));
        order.setBookedSeatStr(bookedSeatStr);

        //改变座位状态为可预定，无绑定的订单
        order.getVenuePlanSeats().forEach(seat -> {
            seat.setAvailable(true);
            seat.setOrder(null);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean retreatOrder(String mail, int orderId) {
        Member member = memberDao.getMember(mail);
        Account account = member.getAccount();
        Order order = orderDao.getOrder(orderId);
        //todo 返还账户余额策略

        //改变订单状态
        order.setOrderStatus(OrderStatus.RETREAT);

        //将订单预订的座位用字符串的形式保存下来
        String bookedSeatStr = order.getVenuePlanSeats().stream()
                .map(seat -> seat.getRow() + "排" + seat.getColumn() + "座")
                .collect(Collectors.joining(","));
        order.setBookedSeatStr(bookedSeatStr);

        //改变座位状态为可预定，无绑定订单
        order.getVenuePlanSeats().forEach(seat -> {
            seat.setAvailable(true);
            seat.setOrder(null);
        });

        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean takeOrderOnSite(TakeOrderDTO takeOrderDTO) {

        Venue venue = venueDao.getVenue(takeOrderDTO.getVenueId());
        VenuePlan venuePlan = venueDao.getVenuePlan(takeOrderDTO.getVenuePlanId());

        Order order = new Order();
        //设定为当前时间
        order.setCreateTime(LocalDateTimeUtil.now());
        //设置订单总价
        order.setPrice(takeOrderDTO.getPrice());
        //订单状态设置为已预订
        order.setOrderStatus(OrderStatus.BOOKED);
        //订单是自选座位
        order.setSeatSettled(true);
        //订单是否是现场购票
        order.setBoughtOnSite(true);

        //判断是否是会员订单
        if (takeOrderDTO.getMemberOrder()) {
            Member member = memberDao.getMember(takeOrderDTO.getMail());
            order.setMemberFK(member);
        }
        order.setVenue(venue);
        order.setVenuePlan(venuePlan);

        //得到persistent状态的场馆计划座位，更新available状态，设置联系order的外键
        List<VenuePlanSeat> selectedVenuePlanSeats = venueDao.getSpecificSeats(takeOrderDTO.getVenuePlanId(), takeOrderDTO.getOrderPlanSeats());
        selectedVenuePlanSeats.forEach(seat -> {
            seat.setAvailable(false);
            seat.setOrder(order);
        });

        order.setVenuePlanSeats(selectedVenuePlanSeats);

        //添加一条订单
        orderDao.addOrder(order);

        //任务调度
//        jobService.addCheckUnpaidOrderJob(order.getCreateTime(), order.getOrderId());

        return true;
    }
}
