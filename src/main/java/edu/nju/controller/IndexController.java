package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Shenmiu
 * @date 2018/03/12
 */
@Controller("indexController")
@RequestMapping("/")
@SessionAttributes(names = {"mail"}, types = {String.class})
public class IndexController {

    @Autowired
    private VenueService venueService;

    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("comingShows", JSON.toJSONString(venueService.getComingVenueBriefPlan()));
        return "index";
    }

}
