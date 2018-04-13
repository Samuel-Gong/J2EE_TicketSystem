package edu.nju.controller;

import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = "file:src/main/webapp/WEB-INF/applicationContext.xml")
class MemberControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private List<SerializerFeature> serializerFeatures;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        serializerFeatures = new ArrayList<>();
        serializerFeatures.add(SerializerFeature.PrettyFormat);
        serializerFeatures.add(SerializerFeature.WriteMapNullValue);
    }

    @Test
    void getInfo() {

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/member/info")
                    .param("memberId", "335931662@qq.com"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(jsonPath("$.totalConsumption").value("0"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void getCouponTypes() {

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/member/couponType")
                    .param("memberId", "335931662@qq.com"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}