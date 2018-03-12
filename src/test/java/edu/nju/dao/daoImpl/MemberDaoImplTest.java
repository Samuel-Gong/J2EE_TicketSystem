package edu.nju.dao.daoImpl;

import edu.nju.dao.MemberDao;
import edu.nju.dao.impl.MemberDaoImpl;
import edu.nju.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class MemberDaoImplTest {

    private ApplicationContext context = null;
    private MemberDao memberDao = null;

    @BeforeEach
    void setUp() {
        context = new ClassPathXmlApplicationContext("beans/daos.xml");
        memberDao = context.getBean("memberDao", MemberDaoImpl.class);
    }

    @Test
    void getMember() {
        Member member = memberDao.getMember("123");
        Assertions.assertEquals(member.getMail(), "123@qq.com");
    }

    @Disabled
    @Test
    void addMember() {
        Member member = new Member("123@qq.com", "12345");
        Assertions.assertEquals(true, memberDao.addMember(member));
    }

    @Test
    void disqulify(){
        memberDao.disqulify("123");
        Assertions.assertEquals(false, memberDao.getMember("123").isQualified());
    }

    @Test
    void updateInfo(){
        Member member = memberDao.getMember("123");
        member.setQualified(true);
        memberDao.updateInfo(member);
        Assertions.assertEquals(true, memberDao.getMember("123").isQualified());
    }
}