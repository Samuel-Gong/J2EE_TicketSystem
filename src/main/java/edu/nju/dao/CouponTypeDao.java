package edu.nju.dao;

import edu.nju.model.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Shenmiu
 * @date 2018/04/13
 */
public interface CouponTypeDao extends JpaRepository<CouponType, Integer> {

    /**
     * 获取所有的优惠券类型
     *
     * @return 所有的优惠券类型列表
     */
    @Override
    List<CouponType> findAll();

    /**
     * 通过优惠券面额获取优惠券的种类
     *
     * @param couponValue 优惠券的面额
     * @return 优惠券的种类
     */
    @Override
    Optional<CouponType> findById(Integer couponValue);
}
