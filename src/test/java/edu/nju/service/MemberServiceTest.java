package edu.nju.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.nju.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

@SpringJUnitConfig(locations = "file:src/main/webapp/WEB-INF/applicationContext.xml")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    private List<SerializerFeature> serializerFeatures;


    @BeforeEach
    void setUp() {
        serializerFeatures = new ArrayList<>();
        serializerFeatures.add(SerializerFeature.PrettyFormat);
        serializerFeatures.add(SerializerFeature.WriteMapNullValue);
    }

    @Test
    void getLevelAndDiscount() {
        System.out.println(JSON.toJSONString(memberService.getLevelAndDiscount("335931662@qq.com")
                , serializerFeatures.toArray(new SerializerFeature[0])));
    }

    @Test
    void getInfo() {
        Member member = memberService.getInfo("335931662@qq.com");
        System.out.println(member.getMail());
        System.out.println(member.getPoints());
    }

    @Test
    void getPointsAndCoupons() {
        System.out.println(JSON.toJSONString(memberService.getPointsAndCoupons("335931662@qq.com")
                , serializerFeatures.toArray(new SerializerFeature[0])));
    }
}