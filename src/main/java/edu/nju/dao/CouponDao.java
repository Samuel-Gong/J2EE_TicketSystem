package edu.nju.dao;

import edu.nju.model.Coupon;
import edu.nju.model.CouponType;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/28
 * <p>
 * 优惠券数据访问接口
 */
public interface CouponDao {

    /**
     * 获取所有的优惠券类型
     *
     * @return 所有的优惠券类型列表
     */
    List<CouponType> getCouponTypes();

    /**
     * 获取会员指定面额的优惠券
     * @param memberId    会员编号
     * @param couponValue 优惠券面额
     * @return 指定面额的优惠券列表
     */
    List<Coupon> getUnusedCoupons(String memberId, int couponValue);

    /**
     * 根据会员ID，获取该会员所有未使用的优惠券
     *
     * @param memberId 会员id
     * @return 未使用的优惠券列表
     */
    List<Coupon> getUnusedCoupons(String memberId);

    /**
     * 通过优惠券面额获取优惠券的种类
     *
     * @param couponValue 优惠券的面额
     * @return 优惠券的种类
     */
    CouponType getCouponType(int couponValue);

    /**
     * 添加一张优惠券
     *
     * @param coupon 优惠券对象
     */
    void addCoupon(Coupon coupon);
}
