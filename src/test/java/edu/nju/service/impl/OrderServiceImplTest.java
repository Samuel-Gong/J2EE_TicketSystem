package edu.nju.service.impl;

import com.alibaba.fastjson.JSON;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.service.OrderService;
import edu.nju.util.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

class OrderServiceImplTest {

    private ApplicationContext context = null;
    private OrderService orderService = null;

    @BeforeEach
    void setUp() {

        //如果是读取/WEB-INF/applicationContext.xml
        context = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
        orderService = context.getBean("orderService", OrderService.class);
    }

    @Test
    void addPickSeatOrder() {

        TakeOrderDTO takeOrderDTO = JSON.parseObject("{\"mail\":\"123@qq.com\",\"venueId\":1,\"venuePlanId\":3,\"orderPlanSeats\":[{\"row\":\"1\",\"column\":\"6\"},{\"row\":\"1\",\"column\":\"7\"},{\"row\":\"1\",\"column\":\"8\"}]}", TakeOrderDTO.class);
        orderService.addPickSeatOrder(takeOrderDTO);
//        takeOrderDTO.set

//        orderService.addPickSeatOrder();

    }

    @Test
    void getOrderShowDTOs() {
        Assertions.assertEquals(1, orderService.getOrderShowDTOs("335931662@qq.com", OrderStatus.UNPAID).size());
    }
}