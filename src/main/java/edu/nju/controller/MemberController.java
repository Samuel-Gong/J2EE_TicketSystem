package edu.nju.controller;

import edu.nju.model.Member;
import edu.nju.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Shenmiu
 * @date 2018/03/05
 */
@Controller("memberController")
@SessionAttributes(names = {"member", "mail"}, types = {Member.class, String.class})
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 用户注册
     *
     * @param member 用户信息
     * @return 重定向首页
     */
    @PostMapping(path = "/register")
    public String register(Member member) {
        memberService.register(member);
        return "redirect:/index";
    }

    /**
     * 会员登录
     *
     * @param mail     会员邮箱
     * @param password 会员密码
     * @return 重定向至首页
     */
    @PostMapping(path = "/login")
    public String login(@RequestParam("mail") String mail, @RequestParam("password") String password, ModelMap modelMap) {
        if (memberService.logIn(mail, password)) {
            modelMap.addAttribute("mail", mail);
            return "redirect:/index";
        }
        //TODO  错误界面
        return "redirect:/error.html";
    }

    /**
     * 邮箱验证
     *
     * @param mail               会员邮箱
     * @param mailKey            邮箱密钥
     * @param redirectAttributes 重定向属性，将会员邮箱保存在session中
     * @return
     */
    @GetMapping(path = "/confirmMail")
    public String confirmMail(@RequestParam("mail") String mail, @RequestParam("mailKey") Integer mailKey, RedirectAttributes redirectAttributes) {
        if (memberService.mailConfirm(mail, mailKey)) {
            redirectAttributes.addFlashAttribute("mail", mail);
            return "redirect:/index";
        }
        //TODO 错误界面
        return "redirect:/error.html";
    }

    /**
     * 查看个人信息
     *
     * @return 个人信息页面
     */
    @GetMapping(path = "/myInfo")
    public String myInfo(@SessionAttribute("mail") String mail) {
        if (mail != null) {
            return "/member/myInfo";
        }
        //TODO 错误界面
        return "redirect:/error.html";
    }

    /**
     * 会员修改密码
     *
     * @param mail        会员邮箱
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return 是否修改成功
     */
    @PostMapping(path = "/modifyPassword")
    public @ResponseBody
    boolean modifyPassword(@SessionAttribute("mail") String mail, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
//        return true;
        return memberService.modifyPassword(mail, oldPassword, newPassword);
    }

    /**
     * 会员取消资格
     *
     * @param mail 会员邮箱
     * @return 是否取消成功
     */
    @GetMapping(path = "/disqualify")
    public @ResponseBody
    boolean disqualify(@SessionAttribute("mail") String mail) {
//        return true;
        return memberService.disqualify(mail);
    }

}
