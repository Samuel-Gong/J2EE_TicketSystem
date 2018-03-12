package edu.nju.controller;

import edu.nju.bo.MemberService;
import edu.nju.model.Member;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Shenmiu
 * @date 2018/03/05
 */
@SessionAttributes(names = {"member", "mail"}, types = {Member.class, String.class})
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    /**
     * 用户注册
     * @param member 用户信息
     * @return 重定向首页
     */
    @PostMapping(path = "register")
    public String register(Member member){
        memberService.register(member);
        return "redirect:/index";
    }

    /**
     * 会员登录
     * @param mail 会员邮箱
     * @param password  会员密码
     * @return 重定向至首页
     */
    @PostMapping(path = "/login")
    public String login(@RequestParam("mail") String mail, @RequestParam("password") String password, ModelMap modelMap){
        if(memberService.logIn(mail, password)){
            modelMap.addAttribute("mail", mail);
            System.out.println("重定向");
            return "redirect:/index";
        }
        //TODO  错误界面
        return "redirect:/error.html";
    }

    /**
     * 邮箱验证
     * @param mail  会员邮箱
     * @param mailKey 邮箱密钥
     * @param redirectAttributes 重定向属性，将会员邮箱保存在session中
     * @return
     */
    @GetMapping(path = "confirmMail")
    public String confirmMail(@RequestParam("mail") String mail, @RequestParam("mailKey") Integer mailKey, RedirectAttributes redirectAttributes){
        if (memberService.mailConfirm(mail, mailKey)){
            redirectAttributes.addFlashAttribute("mail", mail);
            return "redirect:/index";
        }
        //TODO 错误界面
        return "redirect:/error.html";
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

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
