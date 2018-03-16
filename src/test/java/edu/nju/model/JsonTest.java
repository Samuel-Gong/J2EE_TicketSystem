package edu.nju.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.nju.model.embeddable.VenueSeatId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/15
 * <p>
 * 用于测试model的序列化/反序列化
 */
class JsonTest {

    List<SerializerFeature> serializerFeatures;

    @BeforeEach
    void setUp(){
        serializerFeatures = new ArrayList<>();
        serializerFeatures.add(SerializerFeature.PrettyFormat);
        serializerFeatures.add(SerializerFeature.WriteMapNullValue);
    }

    //Venue
    @Test
    void venue2Json() {
        Venue venue = new Venue();
        venue.setColumnNum(10);
        venue.setRowNum(10);
        venue.setName("南京大剧院");
        venue.setCity("南京");
        venue.setPassword("123456");

        VenueSeat venueSeat = new VenueSeat();
        VenueSeatId venueSeatId = new VenueSeatId();
        venueSeatId.setColumn(10);
        venueSeatId.setRow(10);
        venueSeat.setVenueSeatId(venueSeatId);

        venueSeat.setHasSeat(true);

        venue.getSeatMap().add(venueSeat);

        System.out.println(JSON.toJSONString(venue, serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()])));
    }

    @Test
    void parseVenue(){
        String json = "{\n" +
                "\t\"city\":\"南京\",\n" +
                "\t\"columnNum\":10,\n" +
                "\t\"id\":1234567,\n" +
                "\t\"name\":\"南京大剧院\",\n" +
                "\t\"password\":\"123456\",\n" +
                "\t\"rowNum\":10\n" +
                "}";
        Venue venue = JSON.parseObject(json, Venue.class);
        System.out.println(JSON.toJSONString(venue, serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()])));
    }

    //VenueSeat

    @Test
    void venueSeat2JsonString() {
        VenueSeat venueSeat = new VenueSeat();
        VenueSeatId venueSeatId = new VenueSeatId();
        venueSeatId.setColumn(10);
        venueSeatId.setRow(10);
        venueSeat.setVenueSeatId(venueSeatId);

        venueSeat.setHasSeat(true);

        System.out.println(JSON.toJSONString(venueSeat, serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()])));
    }

    @Test
    void parseVenueSeat() {
        String json = "{\n" +
                "\t\"hasSeat\":true,\n" +
                "\t\"venueSeatId\":{\n" +
                "\t\t\"column\":10,\n" +
                "\t\t\"row\":10\n" +
                "\t}\n" +
                "}";
        VenueSeat venueSeat = JSON.parseObject(json, VenueSeat.class);
        System.out.println(venueSeat.isHasSeat());
    }

}
