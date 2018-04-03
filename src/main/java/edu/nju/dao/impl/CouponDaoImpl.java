package edu.nju.dao.impl;

import edu.nju.dao.CouponDao;
import edu.nju.model.Coupon;
import edu.nju.model.CouponType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/28
 * <p>
 * 优惠券数据访问接口实现
 */
@Repository("couponDao")
public class CouponDaoImpl implements CouponDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CouponType> getCouponTypes() {
        return sessionFactory.getCurrentSession()
                .createQuery("from CouponType", CouponType.class)
                .list();
    }

    @Override
    public List<Coupon> getUnusedCoupons(String memberId, int couponValue) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Coupon where memberFK.id = :memberId and used = false and couponType.value = :couponValue", Coupon.class)
                .setParameter("memberId", memberId)
                .setParameter("couponValue", couponValue)
                .list();
    }

    @Override
    public List<Coupon> getUnusedCoupons(String memberId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Coupon where memberFK.id = :memberId and used = false ", Coupon.class)
                .setParameter("memberId", memberId)
                .list();
    }

    @Override
    public CouponType getCouponType(int couponValue) {
        return sessionFactory.getCurrentSession().get(CouponType.class, couponValue);
    }

    @Override
    public void addCoupon(Coupon coupon) {
        sessionFactory.getCurrentSession().save(coupon);
    }
}
