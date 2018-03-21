package edu.nju;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.nju.model.*;
import edu.nju.util.ShowType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
    SerializerFeature features[];

    @BeforeEach
    void setUp() {
        serializerFeatures = new ArrayList<>();
        serializerFeatures.add(SerializerFeature.PrettyFormat);
        serializerFeatures.add(SerializerFeature.WriteMapNullValue);

        features = serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()]);
    }

    //VenuePlan
    @Test
    void venuePlan2Json() {
        VenuePlan venuePlan = new VenuePlan();
        venuePlan.setVenuePlanId(10);
        venuePlan.setBegin(LocalDateTime.of(2018, 1, 1, 10, 0));
        venuePlan.setEnd(LocalDateTime.of(2018, 1, 1, 12, 0));
        venuePlan.setShowType(ShowType.CONCERT);
        venuePlan.setDescription("陈奕迅演唱会");

        List<SeatType> seatTypes = new ArrayList<>();
        SeatType seatType = new SeatType();
        seatType.setTypeChar('a');
        seatType.setPrice(100);
        seatType.setDescription("VIP");
        seatTypes.add(seatType);

        List<VenuePlanSeat> venuePlanSeats = new ArrayList<>();
        VenuePlanSeat venuePlanSeat = new VenuePlanSeat();
        venuePlanSeat.setRow(10);
        venuePlanSeat.setColumn(10);
        venuePlanSeat.setTypeChar('a');
        venuePlanSeats.add(venuePlanSeat);

        venuePlan.setSeatTypes(seatTypes);
        venuePlan.setVenuePlanSeats(venuePlanSeats);

        System.out.println(JSON.toJSONString(venuePlan, features));
    }

    @Test
    void parseVenuePlan() {
        String json = "{\n" +
                "\t\"venuePlanId\":10,\n" +
                "\t\"begin\":\"2018-01-01 10:00\",\n" +
                "\t\"end\":\"2018-01-01 12:00\",\n" +
                "\t\"showType\":\"演唱会\",\n" +
                "\t\"description\":\"陈奕迅演唱会\",\n" +
                "\t\"seatTypes\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"typeChar\":\"a\",\n" +
                "\t\t\t\"price\":100,\n" +
                "\t\t\t\"description\":\"VIP\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"venuePlanSeats\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"row\":10,\n" +
                "\t\t\t\"column\":10,\n" +
                "\t\t\t\"typeChar\":\"a\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        System.out.println(JSON.toJSONString(JSON.parseObject(json, VenuePlan.class), features));
    }

    //SeatType
    @Test
    void seatType2Json() {
        SeatType seatType = new SeatType();
        seatType.setTypeChar('a');
        seatType.setPrice(100);
        seatType.setDescription("VIP");

        System.out.println(JSON.toJSONString(seatType, features));
    }

    //VenuePlanSeat
    @Test
    void venuePlanSeat2Json() {
        VenuePlanSeat venuePlanSeat = new VenuePlanSeat();
        venuePlanSeat.setRow(10);
        venuePlanSeat.setColumn(10);
        venuePlanSeat.setTypeChar('a');

        System.out.println(JSON.toJSONString(venuePlanSeat, features));
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
        venueSeat.setColumn(10);
        venueSeat.setRow(10);

        venueSeat.setHasSeat(true);

        venue.getSeatMap().add(venueSeat);

        System.out.println(JSON.toJSONString(venue, features));
    }

    //VenueSeat
    @Test
    void venueSeat2JsonString() {
        VenueSeat venueSeat = new VenueSeat();
        venueSeat.setColumn(10);
        venueSeat.setRow(10);

        venueSeat.setHasSeat(true);

        System.out.println(JSON.toJSONString(venueSeat, features));
    }

}