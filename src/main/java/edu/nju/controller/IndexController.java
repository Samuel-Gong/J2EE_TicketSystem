package edu.nju.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Shenmiu
 * @date 2018/03/12
 */
@RequestMapping("/")
@SessionAttributes(names = {"mail"}, types = {String.class})
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

}
