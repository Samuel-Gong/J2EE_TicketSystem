package edu.nju.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.nju.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

class MemberServiceTest {

    private ApplicationContext context = null;
    private MemberService memberService = null;

    List<SerializerFeature> serializerFeatures;


    @BeforeEach
    void setUp() {
        serializerFeatures = new ArrayList<>();
        serializerFeatures.add(SerializerFeature.PrettyFormat);
        serializerFeatures.add(SerializerFeature.WriteMapNullValue);

        //如果是读取/WEB-INF/applicationContext.xml
        context = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/applicationContext.xml");
        memberService = context.getBean("memberService", MemberService.class);
    }

    @Test
    void getLevelAndDiscount() {
        System.out.println(JSON.toJSONString(memberService.getLevelAndDiscount("335931662@qq.com")
                , serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()])));
    }

    @Test
    void getInfo(){
        Member member = memberService.getInfo("335931662@qq.com");
        System.out.println(member.getMail());
        System.out.println(member.getPoints());
    }

    @Test
    void getPointsAndCoupons(){
        System.out.println(JSON.toJSONString(memberService.getPointsAndCoupons("335931662@qq.com")
                , serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()])));
    }
}