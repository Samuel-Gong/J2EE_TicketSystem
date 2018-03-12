package edu.nju.controller;

import edu.nju.bo.MemberService;
import edu.nju.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    @PostMapping(path = "register")
    public void register(Member member){
        //TODO
        memberService.register(member);
    }

    @PostMapping(path = "/login")
    public String login(Member member, ModelMap modelMap){
        modelMap.addAttribute("member", member);
        return "member/login";
    }

    @GetMapping(path = "/hello")
    public String printHello(ModelMap modelMap) {
        modelMap.addAttribute("message", "Hello");
        return "member/hello";
    }

    @RequestMapping(path = "/member", method = RequestMethod.GET)
    public ModelAndView member() {
        return new ModelAndView("member/login", "member", new Member());
    }

    @RequestMapping(path = "/addMember", method = RequestMethod.POST)
    public String addMember(@ModelAttribute("SpringWeb") Member member, ModelMap modelMap) {
        modelMap.addAttribute("member", member);
        return "member/result";
    }

    @PostMapping(path = "/getMemberByJson", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView saveMember(@RequestBody Member member){
        return new ModelAndView("member/result", "member", member);
    }

}
