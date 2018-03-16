package edu.nju.dao.impl;

import edu.nju.dao.MemberDao;
import edu.nju.model.Member;
import edu.nju.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * MemberDao接口实现
 */
@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {

    @Override
    public Member getMember(String mail) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Member member = session.createNamedQuery("get_member_by_mail", Member.class)
                .setParameter("mail", mail)
                .getSingleResult();

        tx.commit();
        HibernateUtil.closeSession();

        return member;
    }

    @Override
    public boolean addMember(Member member) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        session.save(member);

        tx.commit();
        HibernateUtil.closeSession();

        return true;
    }

    @Override
    public int disqulify(String mail) {

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        int result = session.createNamedQuery("disqualify")
                .setParameter("mail", mail)
                .executeUpdate();

        tx.commit();
        HibernateUtil.closeSession();

        return result;
    }

    @Override
    public boolean updateMember(Member member) {

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        session.update(member);

        tx.commit();
        HibernateUtil.closeSession();

        return true;
    }

    @Override
    public int getMailKey(String mail) {

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        int mailKey = session.createNamedQuery("get_mailKey_by_mail", Integer.class)
                .setParameter("mail", mail)
                .getSingleResult();

        tx.commit();
        HibernateUtil.closeSession();

        return mailKey;
    }
}
