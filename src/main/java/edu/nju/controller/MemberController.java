package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.dto.MemberOrderDTO;
import edu.nju.model.Member;
import edu.nju.service.MemberService;
import edu.nju.service.OrderService;
import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Shenmiu
 * @date 2018/03/05
 */
@Controller("memberController")
@SessionAttributes(names = {"mail"}, types = {String.class})
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private VenueService venueService;

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "orders")
    public String getOrders(@SessionAttribute("mail") String mail) {
        return "/member/orders";
    }

    /**
     * 用户立即购票
     *
     * @param memberOrderDTO 会员订单的数据传输对象
     * @return 订单是否保存成功
     */
    @PostMapping(path = "/buyNowOrder", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    boolean buyNowOrder(@RequestBody MemberOrderDTO memberOrderDTO) {
        //todo 保存Order
        return true;
    }

    /**
     * 用户选座购票
     *
     * @param memberOrderDTO 会员订单的数据传输对象
     * @return 订单是否保存成功
     */
    @PostMapping(path = "/pickSeatOrder", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    boolean pickSeatOrder(@RequestBody MemberOrderDTO memberOrderDTO) {
        //todo 保存order
        return true;
    }

    /**
     * 用户发起请求订票
     *
     * @param venuePlanId 场馆计划id
     * @return 返回用户订票界面
     */
    @GetMapping(path = "/booking/{venuePlanId}")
    public String booking(@PathVariable("venuePlanId") int venuePlanId, Model model) {
        model.addAttribute("planDetail", JSON.toJSONString(venueService.getVenuePlanDetail(venuePlanId)));
        return "/member/booking";
    }

    /**
     * 浏览演出
     *
     * @return 浏览演出的界面
     */
    @GetMapping(path = "/scanShow")
    public String scanShow(Model model) {
        model.addAttribute("comingShows", JSON.toJSONString(venueService.getComingVenueBriefPlan()));
        return "/member/scan-shows";
    }

    /**
     * 会员登出
     *
     * @param sessionStatus 会话状态
     * @return 重定向至首页
     */
    @GetMapping(path = "logout")
    public String logout(SessionStatus sessionStatus) {
        //会员登出,完成会话
        sessionStatus.setComplete();
        return "redirect:/index";
    }


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
     * @return 重定向至会员浏览场馆计划的界面
     */
    @PostMapping(path = "/login")
    public String login(@RequestParam("mail") String mail, @RequestParam("password") String password, ModelMap modelMap) {
        if (memberService.logIn(mail, password)) {
            modelMap.addAttribute("mail", mail);
            return "redirect:scanShow";
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
