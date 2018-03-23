package edu.nju.service.impl;

import edu.nju.dao.MemberDao;
import edu.nju.dao.OrderDao;
import edu.nju.dao.VenueDao;
import edu.nju.dto.OrderShowDTO;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.dto.VenuePlanBriefDTO;
import edu.nju.model.*;
import edu.nju.service.OrderService;
import edu.nju.util.LocalDateUtil;
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
    public boolean addPickSeatOrder(TakeOrderDTO takeOrderDTO) {
        Member member = memberDao.getMember(takeOrderDTO.getMail());
        Venue venue = venueDao.getVenue(takeOrderDTO.getVenueId());
        VenuePlan venuePlan = venueDao.getVenuePlan(takeOrderDTO.getVenuePlanId());

        Order order = new Order();
        //设定为当前时间
        order.setCreateTime(LocalDateUtil.now());
        //设置订单总价
        order.setPrice(takeOrderDTO.getPrice());
        //订单状态设置为未支付
        order.setOrderStatus(OrderStatus.UNPAID);
        //订单是自选座位，座位
        order.setSeatSettled(true);
        order.setMember(member);
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

        //更新会员及场馆信息
        member.getOrders().add(order);
        venue.getOrders().add(order);

        return true;
    }

    @Override
    public boolean addBuyNowOrder(TakeOrderDTO takeOrderDTO) {
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
        order.setMember(member);
        order.setVenue(venue);
        order.setVenuePlan(venuePlan);

        //添加一条订单
        orderDao.addOrder(order);

        //更新会员及场馆信息
        member.getOrders().add(order);
        venue.getOrders().add(order);

        return true;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<OrderShowDTO> getUnpaidOrdersWithShowInfo(String mail) {
        List<Order> unpaidOrders = orderDao.getOrders(mail, OrderStatus.UNPAID);
        //懒加载每个Order对应选择的座位
        unpaidOrders.forEach(unpaidOrder -> Hibernate.initialize(unpaidOrder.getVenuePlanSeats()));
        return unpaidOrders.stream()
                .map(unpaidOrder -> new OrderShowDTO(unpaidOrder, new VenuePlanBriefDTO(unpaidOrder.getVenuePlan())))
                .collect(Collectors.toList());
    }
}
