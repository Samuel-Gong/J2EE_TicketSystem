package edu.nju.util.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import edu.nju.util.ShowType;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Shenmiu
 * @date 2018/03/17
 * <p>
 * 演出类型为枚举类型，将枚举类型序列化为该类型所对应的演出类型的名称
 */
public class ShowTypeSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        System.out.println(((ShowType) object).getValue());
        serializer.write(((ShowType) object).getValue());
    }
}
