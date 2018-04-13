package edu.nju.dao;

import edu.nju.model.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringJUnitConfig(locations = "/data.xml")
@Transactional
class ManagerDaoTest {

    @Autowired
    private ManagerDao managerDao;

    @Test
    void findById() {
        Optional<Manager> optionalManager = managerDao.findById(1);
        assert optionalManager.isPresent();
        Manager manager = optionalManager.get();
        Assertions.assertEquals("123456", manager.getPassword());
    }
}