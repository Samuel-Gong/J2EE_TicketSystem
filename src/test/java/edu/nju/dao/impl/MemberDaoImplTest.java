package edu.nju.dao.impl;

import edu.nju.dao.MemberDao;
import edu.nju.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

class MemberDaoImplTest {

    private ApplicationContext context = null;
    private MemberDao memberDao = null;

    @BeforeEach
    void setUp() {

        //如果是读取/WEB-INF/applicationContext.xml
        context = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
        memberDao = context.getBean("memberDao", MemberDaoImpl.class);
    }

    @Test
    void getMember() {
        Member member = memberDao.getMember("335931662@qq.com");
        Assertions.assertEquals(member.getMail(), "335931662@qq.com");
    }

//    @Disabled
    @Test
    void addMember() {
        Member member = new Member("123@qq.com", "12345");
        Assertions.assertEquals(true, memberDao.addMember(member));
    }

    @Test
    void disqulify() {
        memberDao.disqulify("335931662@qq.com");
        Assertions.assertEquals(false, memberDao.getMember("335931662@qq.com").isQualified());
    }

    @Test
    void updateInfo() {
        Member member = memberDao.getMember("335931662@qq.com");
        member.setQualified(true);
        memberDao.updateMember(member);
        Assertions.assertEquals(true, memberDao.getMember("335931662@qq.com").isQualified());
    }
}