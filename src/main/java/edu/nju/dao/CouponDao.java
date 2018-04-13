package edu.nju.dao;

import edu.nju.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/28
 * <p>
 * 优惠券数据访问接口
 */
public interface CouponDao extends JpaRepository<Coupon, Integer> {

    /**
     * 获取会员指定面额的优惠券
     *
     * @param memberId    会员编号
     * @param couponValue 优惠券面额
     * @return 指定面额的优惠券列表
     */
    @Query("from Coupon where memberFK.id = :memberId and used = false and couponType.value = :couponValue")
    List<Coupon> getUnusedCoupons(@Param("memberId") String memberId, @Param("couponValue") int couponValue);

    /**
     * 根据会员ID，获取该会员所有未使用的优惠券
     *
     * @param memberId 会员id
     * @return 未使用的优惠券列表
     */
    List<Coupon> getCouponsByMemberFkMailAndUsedIsFalse(String memberId);

    /**
     * 添加一张优惠券
     *
     * @param coupon 优惠券对象
     * @return 添加的优惠券对象
     */
    @Override
    <S extends Coupon> S save(S coupon);
}
