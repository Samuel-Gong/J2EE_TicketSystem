package edu.nju.dao;

import edu.nju.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 经理的数据访问接口
 */
public interface ManagerDao extends JpaRepository<Manager, Integer> {

    /**
     * 通过经理id查找经理对象
     *
     * @param id 经理id
     * @return 经理信息
     */
    Optional<Manager> findById(int id);
}
