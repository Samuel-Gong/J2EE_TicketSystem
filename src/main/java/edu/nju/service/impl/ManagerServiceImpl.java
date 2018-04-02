package edu.nju.service.impl;

import edu.nju.dao.ManagerDao;
import edu.nju.dao.VenueDao;
import edu.nju.model.Manager;
import edu.nju.model.VenuePlan;
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

    @Autowired
    private VenueDao venueDao;

    /**
     * 经理id
     */
    private final static int MEMBER_ID = 1;

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean login(Manager manager) {
        Manager persistentManager = managerDao.getManager(manager.getId());
        //当经理不为空且密码相同时就返回true
        return persistentManager != null && persistentManager.getPassword().equals(manager.getPassword());
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Manager getManager(int managerId) {
        Manager manager = managerDao.getManager(managerId);
        assert manager != null;
        return manager;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean settlePlan(int venuePlanId, int rate) {
        VenuePlan venuePlan = venueDao.getVenuePlan(venuePlanId);
        int totalIncome = venuePlan.getTotalIncome();
        int actualIncome = totalIncome * rate / 10;

        //计划被结算
        venuePlan.setSettle(true);

        //场馆计划实际票价收入
        venuePlan.setActualIncome(actualIncome);

        //从未结算总收入中扣除票价总收入
        Manager manager = managerDao.getManager(MEMBER_ID);
        manager.setUnsettleIncome(manager.getUnsettleIncome() - totalIncome);
        //系统赚取差价
        manager.setSettleIncome(manager.getSettleIncome() + totalIncome - actualIncome);

        return true;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Manager getFinance() {
        return managerDao.getManager(MEMBER_ID);
    }
}
