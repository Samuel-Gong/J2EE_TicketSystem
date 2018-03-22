package edu.nju.service.impl;

import edu.nju.dao.MemberDao;
import edu.nju.dao.OrderDao;
import edu.nju.dao.VenueDao;
import edu.nju.dto.MemberOrderDTO;
import edu.nju.model.Member;
import edu.nju.model.Order;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlanSeat;
import edu.nju.service.OrderService;
import edu.nju.util.LocalDateTimeUtil;
import edu.nju.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public boolean addPickSeatOrder(MemberOrderDTO memberOrderDTO) {
        Member member = memberDao.getMember(memberOrderDTO.getMail());
        Venue venue = venueDao.getVenue(memberOrderDTO.getVenueId());

        Order order = new Order();
        //设定为当前时间
        order.setCreateTime(LocalDateTimeUtil.now());
        //订单状态设置为已配票
        order.setOrderStatus(OrderStatus.ARRANGED);
        order.setMember(member);
        order.setVenue(venue);

        //得到persistent状态的场馆计划座位，更新available状态，设置联系order的外键
        List<VenuePlanSeat> selectedVenuePlanSeats = venueDao.getSpecificSeats(memberOrderDTO.getVenuePlanId(), memberOrderDTO.getOrderPlanSeats());
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
    public boolean addBuyNowOrder(MemberOrderDTO memberOrderDTO) {
        return false;
    }
}
