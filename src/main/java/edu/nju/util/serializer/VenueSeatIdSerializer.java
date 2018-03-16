package edu.nju.util.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import edu.nju.model.embeddable.VenueSeatId;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Shenmiu
 * @date 2018/03/15
 */
public class VenueSeatIdSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        VenueSeatId venueSeatId = (VenueSeatId) object;

    }
}
