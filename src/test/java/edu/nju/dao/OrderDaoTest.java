package edu.nju.dao;

import edu.nju.util.enums.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(locations = "/data.xml")
@Transactional
class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    void getOrdersByVenuePlanVenuePlanId() {
        Assertions.assertEquals(2, orderDao.getOrdersByVenuePlanVenuePlanId(4).size());
    }

    @Test
    void getOrdersByOrderStatus() {
        Assertions.assertEquals(4, orderDao.getOrdersByOrderStatus(OrderStatus.CANCELED).size());
    }

    @Test
    void getOrdersByMemberFkMailAndOrderStatusAndBoughtOnlineIsTrue() {
        Assertions.assertEquals(4, orderDao.getOrdersByMemberFkMailAndOrderStatusAndBoughtOnlineIsTrue("a52212498@163.com", OrderStatus.CANCELED).size());
    }
}