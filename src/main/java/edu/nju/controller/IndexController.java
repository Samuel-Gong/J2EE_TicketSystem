package edu.nju.controller;

import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shenmiu
 * @date 2018/03/12
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController {

    @Autowired
    private VenueService venueService;

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("comingShows", venueService.getComingVenueBriefPlan());
        return "/index";
    }

}