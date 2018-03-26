package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.dto.LevelAndDiscount;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.model.Member;
import edu.nju.service.MemberService;
import edu.nju.service.OrderService;
import edu.nju.service.VenueService;
import edu.nju.util.OrderStatus;
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

    @RequestMapping(path = "/discount")
    public @ResponseBody
    LevelAndDiscount getMemberDiscount(@RequestParam("mail") String mail) {
        return memberService.getLevelAndDiscount(mail);
//        return new LevelAndDiscount();
    }

    /**
     * 订单退订
     *
     * @param mail    会员邮箱
     * @param orderId 订单编号
     * @return 订单退订是否成功
     */
    @GetMapping(path = "/order/retreat/{orderId}")
    public @ResponseBody
    boolean retreatOrder(@SessionAttribute("mail") String mail, @PathVariable("orderId") int orderId) {
        return orderService.retreatOrder(mail, orderId);
    }

    /**
     * 取消订单支付
     *
     * @param orderId 订单编号
     * @return 取消支付是否成功
     */
    @GetMapping(path = "/order/cancel/{orderId}")
    public @ResponseBody
    boolean cancelOrder(@PathVariable("orderId") int orderId) {
        return orderService.cancelOrder(orderId);
    }

    /**
     * 订单支付
     *
     * @param orderId 订单编号
     * @return 支付是否成功
     */
    @GetMapping(path = "/order/pay/{orderId}")
    public @ResponseBody
    boolean payOrder(@SessionAttribute("mail") String mail, @PathVariable("orderId") int orderId) {
        return orderService.payOrder(mail, orderId);
    }

    /**
     * 请求支付订单
     *
     * @param orderId 订单编号
     * @return 支付订单界面
     */
    @GetMapping(path = "/pay/{orderId}")
    public String payView(@PathVariable("orderId") int orderId, Model model) {
        model.addAttribute("unpaidOrder", orderService.getOrderShowDTO(orderId));
        return "member/pay";
    }


    /**
     * 未支付订单
     *
     * @param mail 会员邮箱
     * @return 未支付订单
     */
    @GetMapping(path = "/order/unpaid")
    public String unpaidOrder(@SessionAttribute("mail") String mail, Model model) {
        //首先进去展示未支付订单
        model.addAttribute("unpaidOrders", orderService.getOrderShowDTOs(mail, OrderStatus.UNPAID));
        return "/member/order/unpaid";
    }

    /**
     * 已预订订单
     *
     * @param mail 会员邮箱
     * @return 已预订订单界面
     */
    @GetMapping(path = "/order/booked")
    public String bookedOrder(@SessionAttribute("mail") String mail, Model model) {
        model.addAttribute("bookedOrders", orderService.getOrderShowDTOs(mail, OrderStatus.BOOKED));
        return "/member/order/booked";
    }

    /**
     * 已消费订单
     *
     * @param mail 会员邮箱
     * @return 已消费订单界面
     */
    @GetMapping(path = "/order/consumed")
    public String consumedOrder(@SessionAttribute("mail") String mail, Model model) {
        model.addAttribute("consumedOrders", orderService.getOrderShowDTOs(mail, OrderStatus.COMSUMPED));
        return "/member/order/consumed";
    }

    /**
     * 已退订订单
     *
     * @param mail 会员邮箱
     * @return 已退订订单界面
     */
    @GetMapping(path = "/order/retreat")
    public String retreatOrder(@SessionAttribute("mail") String mail, Model model) {
        model.addAttribute("retreatOrders", orderService.getOrderShowDTOs(mail, OrderStatus.RETREAT));
        return "/member/order/retreat";
    }

    /**
     * 已取消支付订单
     *
     * @param mail 会员邮箱
     * @return 已取消订单
     */
    @GetMapping(path = "/order/cancel")
    public String cancelOrder(@SessionAttribute("mail") String mail, Model model) {
        model.addAttribute("cancelOrders", orderService.getOrderShowDTOs(mail, OrderStatus.CANCELED));
        return "/member/order/cancel";
    }

    /**
     * 用户选座购票
     *
     * @param takeOrderDTO 会员订单的数据传输对象
     * @return 被保存订单的编号
     */
    @PostMapping(path = "/pickSeatOrder", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    int pickSeatOrder(@RequestBody TakeOrderDTO takeOrderDTO) {
        return orderService.addPickSeatOrder(takeOrderDTO);
    }

    /**
     * 用户立即购票
     *
     * @param takeOrderDTO 会员订单的数据传输对象
     * @return 被保存订单的编号
     */
    @PostMapping(path = "/buyNowOrder", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    int buyNowOrder(@RequestBody TakeOrderDTO takeOrderDTO) {
        return orderService.addBuyNowOrder(takeOrderDTO);
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
        model.addAttribute("comingShows", venueService.getComingVenueBriefPlan());
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
