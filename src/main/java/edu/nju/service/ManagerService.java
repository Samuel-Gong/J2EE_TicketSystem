package edu.nju.service;

import edu.nju.model.Manager;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 经理业务逻辑接口
 */
public interface ManagerService {
    /**
     * 经理登录
     *
     * @param id       经理id
     * @param password 经理密码
     * @return 登录是否成功
     */
    boolean login(int id, String password);

    /**
     * 根据经理id获取经理信息
     *
     * @param managerId 经理id
     * @return 经理信息
     */
    Manager getManager(int managerId);

    /**
     * 结算场馆计划
     *
     * @param venuePlanId 场馆计划id
     * @param rate        场馆利率
     * @return 是否结算成功
     */
    boolean settlePlan(int venuePlanId, int rate);

    /**
     * 获取Tickets财务情况
     *
     * @return 经理对象
     */
    Manager getFinance();
}
