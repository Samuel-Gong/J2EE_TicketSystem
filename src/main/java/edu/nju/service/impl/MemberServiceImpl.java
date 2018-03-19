package edu.nju.service.impl;

import edu.nju.dao.MemberDao;
import edu.nju.model.Member;
import edu.nju.service.MemberService;
import edu.nju.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

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
