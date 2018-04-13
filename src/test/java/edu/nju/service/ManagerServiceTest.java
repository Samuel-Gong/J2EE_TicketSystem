package edu.nju.service;

import edu.nju.model.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "file:src/main/webapp/WEB-INF/applicationContext.xml")
class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;

    @Test
    void getFinance() {
        Manager manager = managerService.getFinance();
        System.out.println(manager.getSettleIncome());
    }
}