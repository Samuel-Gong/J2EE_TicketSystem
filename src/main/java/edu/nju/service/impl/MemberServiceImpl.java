package edu.nju.service.impl;

import edu.nju.dao.AccountDao;
import edu.nju.dao.CouponDao;
import edu.nju.dao.MemberDao;
import edu.nju.dto.LevelAndDiscount;
import edu.nju.dto.MemberStatistics;
import edu.nju.dto.PointsAndCoupons;
import edu.nju.model.Account;
import edu.nju.model.Member;
import edu.nju.model.Order;
import edu.nju.service.MemberService;
import edu.nju.service.strategy.DiscountStrategy;
import edu.nju.service.strategy.LevelStrategy;
import edu.nju.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    private AccountDao accountDao;

    private static final String DOMAIN = "http://localhost:8888/tickets/member/confirmMail?";

    @Override
    @Transactional(rollbackFor = RuntimeException.class, timeout = 60000)
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
    public boolean logIn(Member member) {
        Member persistentMember = memberDao.getMember(member.getMail());
        //会员存在且密码正确返回true
        return persistentMember != null && persistentMember.getPassword().equals(member.getPassword());
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
            //根据会员总消费计算会员等级
            int totalConsumption = member.getTotalConsumption();
            int level = LevelStrategy.calculateLevel(totalConsumption);
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

        Map<Integer, Long> valueAndRemain = couponDao.getUnusedCoupons(memberId).stream()
                .collect(Collectors.groupingBy(coupon -> coupon.getCouponType().getValue(), Collectors.counting()));

        pointsAndCoupons.setCoupons(valueAndRemain);

        return pointsAndCoupons;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean bindAccount(String mail, int accountId, String accountPassword) {
        Member member = memberDao.getMember(mail);
        assert member.getAccount() == null;
        Account accountInDB = accountDao.getAccount(accountId);

        //账户存在且密码相同，将支付宝账户绑定到会员上
        if (accountInDB != null && accountInDB.getPassword().equals(accountPassword)) {
            //建立联系
            member.setAccount(accountInDB);
            member.setBindAccount(true);
            accountInDB.setMember(member);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public MemberStatistics getMemberStatistics(String mail) {

        Member member = memberDao.getMember(mail);
        List<Order> orders = member.getOrders();

        //预订订单金额
        int totalBooked = 0;
        //消费订单金额
        int totalConsumed = 0;

        //退订订单退还金额
        int totalRefund = 0;
        //退订订单预订金额
        int totalRefundBooked = 0;

        for (Order order : orders) {
            switch (order.getOrderStatus()) {
                case BOOKED:
                    totalBooked += order.getActualPrice();
                    break;
                case COMSUMPED:
                    totalConsumed += order.getActualPrice();
                    break;
                case RETREAT:
                    totalRefundBooked += order.getActualPrice();
                    totalRefund += order.getRefund();
                    break;
                default:
                    break;
            }
        }

        //通过退订订单退还金额和预订金额计算总手续费
        int totalFee = totalRefundBooked - totalRefund;

        return new MemberStatistics(totalBooked, totalConsumed, totalRefund, totalFee);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Map<Integer, Long> getMemberDistribution() {
        List<Integer> allMembersPoints = memberDao.getAllMembersPoints();

        return allMembersPoints.stream()
                .map(LevelStrategy::calculateLevel)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean checkAccount(String mail) {
        Member member = memberDao.getMember(mail);
        return member.getAccount() != null;
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
