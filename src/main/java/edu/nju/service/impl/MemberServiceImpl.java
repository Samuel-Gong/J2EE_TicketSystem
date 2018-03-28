package edu.nju.service.impl;

import edu.nju.dao.CouponDao;
import edu.nju.dao.MemberDao;
import edu.nju.dto.LevelAndDiscount;
import edu.nju.dto.PointsAndCoupons;
import edu.nju.model.Coupon;
import edu.nju.model.Member;
import edu.nju.service.DiscountStrategy;
import edu.nju.service.LevelStrategy;
import edu.nju.service.MemberService;
import edu.nju.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * Member业务逻辑实现
 */
@Service("memberService")
@Transactional(rollbackFor = RuntimeException.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private CouponDao couponDao;

    private static final String DOMAIN = "http://localhost:8888/member/confirmMail?";

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean register(Member member) {

        //生成密钥
        member.setMailKey(generateKey());
        //资格设置为true
        member.setQualified(true);

        String msg = DOMAIN + "mail" + "=" + member.getMail() + "&&" + "mailKey" + "=" + member.getMailKey();

        //返回是否注册成功，且邮件是否发送成功
        return memberDao.addMember(member) && MailUtil.sendMail(member.getMail(), msg);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean mailConfirm(String mail, int mailKey) {
        return mailKey == memberDao.getMember(mail).getMailKey();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean logIn(String mail, String password) {
        return password.equals(memberDao.getMember(mail).getPassword());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modifyPassword(String mail, String oldPassword, String newPassword) {
        Member member = memberDao.getMember(mail);
        if (oldPassword.equals(member.getPassword())) {
            member.setPassword(newPassword);
            return updateInfo(member);
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean disqualify(String mail) {
        return memberDao.disqulify(mail) > 0;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public LevelAndDiscount getLevelAndDiscount(String mail) {
        Member member = memberDao.getMember(mail);
        LevelAndDiscount levelAndDiscount = new LevelAndDiscount();
        //如果会员不存在，将等级设置为-1
        if (member == null) {
            levelAndDiscount.setLevel(-1);
            levelAndDiscount.setDiscount(10);
        } else {
            int points = member.getPoints();
            int level = LevelStrategy.calculateLevel(points);
            levelAndDiscount.setLevel(level);
            levelAndDiscount.setDiscount(DiscountStrategy.calculateDiscount(level));
        }
        return levelAndDiscount;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Member getInfo(String memberId) {
        return memberDao.getMember(memberId);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public PointsAndCoupons getPointsAndCoupons(String memberId) {
        PointsAndCoupons pointsAndCoupons = new PointsAndCoupons();
        Member member = memberDao.getMember(memberId);
        pointsAndCoupons.setPoints(member.getPoints());

        //todo 使用lambda简化
        List<Coupon> couponList = couponDao.getUnusedCoupons(memberId);
        Map<Integer, Integer> valueAndPoints = new TreeMap<>();
        for (Coupon coupon : couponList) {
            int value = coupon.getCouponType().getValue();
            //如果存在就+1
            valueAndPoints.computeIfPresent(value, (key, oldValue) -> oldValue + 1);
            //如果不存在就赋值为1
            valueAndPoints.putIfAbsent(value, 1);
        }

        pointsAndCoupons.setCoupons(valueAndPoints);

        return pointsAndCoupons;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateInfo(Member member) {
        return memberDao.updateMember(member);
    }

    /**
     * 生成验证邮箱的公钥
     *
     * @return 公钥的数字
     */
    private int generateKey() {
        return new Random().nextInt();
    }
}
