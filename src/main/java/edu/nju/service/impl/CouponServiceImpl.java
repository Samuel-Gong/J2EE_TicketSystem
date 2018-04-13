package edu.nju.service.impl;

import edu.nju.dao.CouponDao;
import edu.nju.dao.CouponTypeDao;
import edu.nju.dao.MemberDao;
import edu.nju.model.Coupon;
import edu.nju.model.CouponType;
import edu.nju.model.Member;
import edu.nju.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Shenmiu
 * @date 2018/03/28
 * <p>
 * 优惠券业务逻辑实现
 */
@Service("couponService")
@Transactional(rollbackFor = RuntimeException.class)
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private CouponTypeDao couponTypeDao;

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public List<CouponType> getCouponTypes() {
        return couponTypeDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean exchangeCoupon(String memberId, int couponValue) {
        Member member = memberDao.findById(memberId).get();
        Optional<CouponType> optionalCouponType = couponTypeDao.findById(couponValue);
        assert optionalCouponType.isPresent();

        CouponType couponType = optionalCouponType.get();

        //减去会员的积分
        member.setPoints(member.getPoints() - couponType.getRequiredPoints());

        Coupon coupon = new Coupon();
        //和优惠券类型以及会员建立联系
        coupon.setCouponType(couponType);
        coupon.setMemberFk(member);

        //持久化
        couponDao.save(coupon);

        return true;
    }
}
