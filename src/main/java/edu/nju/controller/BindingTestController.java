package edu.nju.controller;

import edu.nju.BindingTest.bindingTestModel.ComplexUser;
import edu.nju.BindingTest.bindingTestModel.User;
import edu.nju.BindingTest.bindingTestModel.UserListVO;
import edu.nju.BindingTest.bindingTestModel.UserMapVO;
import edu.nju.BindingTest.converters.LocalDateConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

/**
 * @author Shenmiu
 * @date 2018/03/07
 * <p>
 * 用于测试Spring的参数绑定
 */
@Controller("bindingTestController")
@RequestMapping("")
public class BindingTestController {

    //基本类型

    /**
     * int型
     */
    @RequestMapping("/intData")
    public @ResponseBody
    int intData(@RequestParam(value = "intData") int a) {
        return a;
    }

    /**
     * float型
     */
    @RequestMapping("/floatData")
    public @ResponseBody
    float floatData(@RequestParam(value = "floatData") int a) {
        return a;
    }

    /**
     * boolean
     */
    @RequestMapping("/booleanData")
    public @ResponseBody
    boolean booleanData(@RequestParam(value = "booleanData") boolean a) {
        return a;
    }

    //包装数据类型

    /**
     * Integer
     */
    @RequestMapping("/integerData")
    public @ResponseBody
    Integer integerData(@RequestParam(value = "integerData") Integer a) {
        return a;
    }


    /**
     * 简单POJO
     */
    @RequestMapping("pojo")
    public @ResponseBody
    User pojoData(User user) {
        return user;
    }

    /**
     * 复合POJO
     */
    @RequestMapping(value = "complexPOJO", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    ComplexUser complexPOJOData(ComplexUser complexUser) {
        return complexUser;
    }

    //集合类

    /**
     * List
     */
    @RequestMapping(value = "list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    UserListVO listData(UserListVO userListVO) {
        return userListVO;
    }

    /**
     * Map
     */
    @RequestMapping(value = "map", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    UserMapVO mapData(UserMapVO userMapVO) {
        return userMapVO;
    }

    //Converter

    @RequestMapping(value = "stringToLocalDate")
    public @ResponseBody
    String localDateData(@RequestParam("date") LocalDate date) {
        return date.toString();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        GenericConversionService conversionService = (GenericConversionService) webDataBinder.getConversionService();

        //Try to add converter manually, it can convert String to LocalDate successfully
        conversionService.addConverter(String.class, LocalDate.class, new LocalDateConverter("yyyy-MM-dd"));
//        System.out.println(conversionService.convert("2000-10-10", LocalDate.class));

        //If I don't add the converter manually, this also returns true.
        //System.out.println(conversionService.canConvert(String.class, LocalDate.class));

        //But the convert method below fails with DateTimeParseException, I don't know why
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //System.out.println(LocalDate.parse("2000-10-10", dateTimeFormatter.withLocale()));

    }

}
