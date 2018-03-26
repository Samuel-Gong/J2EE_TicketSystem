package edu.nju.service.impl;

import edu.nju.dao.ManagerDao;
import edu.nju.model.Manager;
import edu.nju.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 经理业务逻辑的实现类
 */
@Service("managerService")
@Transactional(rollbackFor = RuntimeException.class)
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean login(int id, String password) {
        Manager manager = managerDao.getManager(id);
        return manager != null && manager.getPassword().equals(password);
    }
}
