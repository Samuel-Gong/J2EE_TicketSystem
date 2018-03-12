package edu.nju.dao.impl;

import edu.nju.dao.MemberDao;
import edu.nju.model.Member;
import edu.nju.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * MemberDao接口实现
 */
public class MemberDaoImpl implements MemberDao {

    @Override
    public Member getMember(String id) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Member member = session.createNamedQuery("get_member_by_id", Member.class)
                .setParameter("id", id)
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
    public int disqulify(String id) {

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        int result = session.createNamedQuery("disqulify")
                .setParameter("id", id)
                .executeUpdate();

        tx.commit();
        HibernateUtil.closeSession();

        return result;
    }

    @Override
    public boolean updateInfo(Member member) {

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        session.update(member);

        tx.commit();
        HibernateUtil.closeSession();

        return true;
    }
}
