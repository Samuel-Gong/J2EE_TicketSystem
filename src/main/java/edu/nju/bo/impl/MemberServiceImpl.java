package edu.nju.bo.impl;

import edu.nju.bo.MemberService;
import edu.nju.dao.MemberDao;
import edu.nju.model.Member;
import edu.nju.util.MailUtil;

import java.util.Random;

/**
 * @author Shenmiu
 * @date 2018/03/04
 *
 * Member业务逻辑实现
 */
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao;

    private static final String DOMAIN = "http://localhost:8888/member/confirmMail?";

    @Override
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
    public boolean mailConfirm(String mail, int mailKey) {
        return mailKey == memberDao.getMember(mail).getMailKey();
    }

    @Override
    public boolean logIn(String mail, String password) {
        return password.equals(memberDao.getMember(mail).getPassword());
    }

    @Override
    public boolean updateInfo(Member member) {
        return false;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    /**
     * 生成验证邮箱的公钥
     * @return 公钥的数字
     */
    private int generateKey() {
        return new Random().nextInt();
    }
}
