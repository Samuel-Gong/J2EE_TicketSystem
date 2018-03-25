import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.model.VenuePlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/23
 */
class JsonTest {

    List<SerializerFeature> serializerFeatures;

    @BeforeEach
    void setUp() {
        serializerFeatures = new ArrayList<>();
        serializerFeatures.add(SerializerFeature.PrettyFormat);
        serializerFeatures.add(SerializerFeature.WriteMapNullValue);
    }

    @Test
    void VenuePlan() {
        VenuePlan venuePlan = JSON.parseObject("" +
                "{\"begin\":\"2018-03-28 16:00\",\"end\":\"2018-03-28 18:00\",\"showType\":\"音乐会\",\"description\":\"周杰伦演唱会\",\"seatTypes\":[{\"typeChar\":\"b\",\"price\":\"100\",\"description\":\"二等座\"},{\"typeChar\":\"c\",\"price\":\"200\",\"description\":\"一等座\"}],\"venuePlanSeats\":[{\"row\":1,\"column\":1,\"typeChar\":\"c\"},{\"row\":1,\"column\":2,\"typeChar\":\"c\"},{\"row\":1,\"column\":3,\"typeChar\":\"c\"},{\"row\":1,\"column\":4,\"typeChar\":\"c\"},{\"row\":1,\"column\":5,\"typeChar\":\"c\"},{\"row\":1,\"column\":6,\"typeChar\":\"c\"},{\"row\":1,\"column\":7,\"typeChar\":\"c\"},{\"row\":1,\"column\":8,\"typeChar\":\"c\"},{\"row\":1,\"column\":9,\"typeChar\":\"c\"},{\"row\":1,\"column\":10,\"typeChar\":\"c\"},{\"row\":1,\"column\":11,\"typeChar\":\"c\"},{\"row\":2,\"column\":1,\"typeChar\":\"b\"},{\"row\":2,\"column\":2,\"typeChar\":\"b\"},{\"row\":2,\"column\":3,\"typeChar\":\"b\"},{\"row\":2,\"column\":4,\"typeChar\":\"b\"},{\"row\":2,\"column\":5,\"typeChar\":\"b\"},{\"row\":2,\"column\":6,\"typeChar\":\"b\"},{\"row\":2,\"column\":7,\"typeChar\":\"b\"},{\"row\":2,\"column\":8,\"typeChar\":\"b\"},{\"row\":2,\"column\":9,\"typeChar\":\"b\"},{\"row\":2,\"column\":10,\"typeChar\":\"b\"},{\"row\":2,\"column\":11,\"typeChar\":\"b\"},{\"row\":3,\"column\":1,\"typeChar\":\"b\"},{\"row\":3,\"column\":2,\"typeChar\":\"b\"},{\"row\":3,\"column\":3,\"typeChar\":\"b\"},{\"row\":3,\"column\":4,\"typeChar\":\"b\"},{\"row\":3,\"column\":5,\"typeChar\":\"b\"},{\"row\":3,\"column\":6,\"typeChar\":\"b\"},{\"row\":3,\"column\":7,\"typeChar\":\"b\"},{\"row\":3,\"column\":8,\"typeChar\":\"b\"},{\"row\":3,\"column\":9,\"typeChar\":\"b\"},{\"row\":3,\"column\":10,\"typeChar\":\"b\"},{\"row\":3,\"column\":11,\"typeChar\":\"b\"}]}", VenuePlan.class);
        System.out.println(JSON.toJSONString(venuePlan, serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()])));
    }

    @Test
    void takeOrderDTO() {
        TakeOrderDTO takeOrderDTO = JSON.parseObject("" +
                "{\"mail\":\"335931662@qq.com\",\"memberOrder\":true,\"venueId\":1000000,\"venuePlanId\":1,\"createTime\":\"2018-03-25 22:40:58\",\"orderPlanSeats\":[{\"row\":\"2\",\"column\":\"4\"},{\"row\":\"2\",\"column\":\"5\"},{\"row\":\"2\",\"column\":\"6\"}],\"price\":420}", TakeOrderDTO.class);
        System.out.println(JSON.toJSONString(takeOrderDTO, serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()])));
    }

}
