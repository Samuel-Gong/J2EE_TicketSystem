package edu.nju.service;

import edu.nju.model.CouponType;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/28
 * <p>
 * 优惠券业务逻辑接口
 */
public interface CouponService {

    /**
     * 获取所有的优惠券类型
     *
     * @return 优惠券类型列表
     */
    List<CouponType> getCouponTypes();

    /**
     * 兑换一张优惠券
     *
     * @param memberId    会员id
     * @param couponValue 优惠券面额
     * @return 兑换是否成功
     */
    boolean exchangeCoupon(String memberId, int couponValue);
}
