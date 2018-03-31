package edu.nju.dao.impl;

import edu.nju.dao.MemberDao;
import edu.nju.model.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/04
 * <p>
 * MemberDao接口实现
 */
@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Member getMember(String mail) {
        return sessionFactory.getCurrentSession().get(Member.class, mail);
    }

    @Override
    public boolean addMember(Member member) {
        sessionFactory.getCurrentSession().save(member);
        return true;
    }

    @Override
    public int disqulify(String mail) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Member m set m.qualified = 0 where m.mail = :mail")
                .setParameter("mail", mail);

        return query.executeUpdate();
    }

    @Override
    public boolean updateMember(Member member) {
        Session session = sessionFactory.getCurrentSession();
        session.update(member);
        return true;
    }

    @Override
    public int getMailKey(String mail) {
        Session session = sessionFactory.getCurrentSession();
        Query<Integer> query = session.createQuery("select mailKey from Member where mail = :mail", Integer.class)
                .setParameter("mail", mail);
        return query.getSingleResult();
    }

    @Override
    public List<Integer> getAllMembersPoints() {
        return sessionFactory.getCurrentSession()
                .createQuery("select points from Member", Integer.class)
                .list();
    }
}
