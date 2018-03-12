package edu.nju.controller;

import edu.nju.model.Member;
import edu.nju.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Shenmiu
 * @date 2018/03/05
 */
@Controller
@SessionAttributes("member")
@RequestMapping("/venue")
public class VenueController {

    @RequestMapping(value = "/venue", method = RequestMethod.GET)
    public ModelAndView venue(){
        return new ModelAndView("venue/venue", "venue", new Venue());
    }

    @RequestMapping(value = "/addVenue", method = RequestMethod.POST)
    public String addVenue(@ModelAttribute("SpringWeb")Venue venue, ModelMap modelMap){
        modelMap.addAttribute("venue", venue);
        return "venue/result";
    }

    @GetMapping(value = "/testSession")
    public String testSession(@SessionAttribute("member")Member memberSession, @ModelAttribute("member")Member member){
        System.out.println(memberSession.getMail());
        return "venue/testSession";
    }

}
