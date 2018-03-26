package edu.nju.service;

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
}
