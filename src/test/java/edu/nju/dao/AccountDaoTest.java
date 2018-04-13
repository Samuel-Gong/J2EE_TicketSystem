package edu.nju.dao;

import edu.nju.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(locations = "/data.xml")
@Transactional
class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    void findById() {
        Account account = accountDao.findById(2).get();
        Assertions.assertEquals(20000, account.getBalance());
    }
}