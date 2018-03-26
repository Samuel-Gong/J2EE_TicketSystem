package edu.nju.dao;

import edu.nju.model.Manager;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 经理的数据访问接口
 */
public interface ManagerDao {

    /**
     * 通过经理id查找经理对象
     *
     * @param id 经理id
     * @return 经理信息
     */
    Manager getManager(int id);

}
