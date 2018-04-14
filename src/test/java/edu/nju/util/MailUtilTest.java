package edu.nju.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class MailUtilTest {

    private ApplicationContext context;
    private MailUtil mailUtil;

    @BeforeEach
    void setUp() {
        context = new ClassPathXmlApplicationContext("mail.xml");
        mailUtil = context.getBean("mailUtil", MailUtil.class);
    }

    @Test
    void sendMail() {
        mailUtil.sendMail("335931662@qq.com", "www.baidu.com");
    }
}