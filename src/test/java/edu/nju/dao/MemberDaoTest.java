package edu.nju.dao;

import edu.nju.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringJUnitConfig(locations = "file:src/main/webapp/WEB-INF/applicationContext.xml")
@Transactional
class MemberDaoTest {

    @Autowired
    private MemberDao memberDao;

    @Test
    void getMember() {
        Optional<Member> optionalMember = memberDao.findById("335931662@qq.com");
        Assertions.assertTrue(optionalMember.isPresent());
        Member member = optionalMember.get();
        Assertions.assertEquals(member.getPassword(), "123456");
    }

    @Test
    void addMember() {

        long oldColumn = memberDao.count();

        Member member = new Member("123@qq.com", "12345");
        memberDao.save(member);

        Assertions.assertEquals(3, (int) oldColumn + 1);
    }

    @Test
    void updateInfo() {
        Member member = memberDao.findById("335931662@qq.com").get();
        member.setPassword("1234567");
        memberDao.save(member);

        Assertions.assertEquals("1234567", memberDao.findById("335931662@qq.com").get().getPassword());
    }

    @Test
    void getAllMemberPoints() {
        Assertions.assertEquals(2, memberDao.getAllMembersPoints().size());
    }
}