package edu.nju.util.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import edu.nju.util.enums.ShowType;

import java.lang.reflect.Type;

/**
 * @author Shenmiu
 * @date 2018/03/17
 * <p>
 * 将演出类型的字符串转化为枚举类型
 */
public class ShowTypeDeserializer implements ObjectDeserializer {


    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer lexer = parser.getLexer();
        String showType = lexer.stringVal();
        return (T) ShowType.val2name(showType);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
