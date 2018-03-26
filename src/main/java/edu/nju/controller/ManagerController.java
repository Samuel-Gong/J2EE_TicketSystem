package edu.nju.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import edu.nju.model.Venue;
import edu.nju.service.ManagerService;
import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/26
 * <p>
 * 经理界面的控制器
 */
@Controller("managerController")
@SessionAttributes(names = {"managerId"}, types = {Integer.class})
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private VenueService venueService;

    /**
     * 经理登出
     *
     * @return 重定向到网站主页
     */
    @GetMapping(path = "logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/index";
    }

    /**
     * 审批场馆
     *
     * @param venueId 场馆编号
     * @return 审批是否通过
     */
    @PostMapping(path = "/audit/pass/{venueId}")
    public @ResponseBody
    boolean auditPass(@PathVariable("venueId") int venueId) {
        return venueService.auditPass(venueId);
    }


    /**
     * 获取需审批场馆的详情
     *
     * @param venueId 场馆编号
     * @return 场馆详情
     */
    @FastJsonView(exclude = @FastJsonFilter(clazz = Venue.class, props = {"password"}))
    @GetMapping(path = "/audit/venue/detail/{venueId}")
    public @ResponseBody
    Venue getAuditVenue(@PathVariable("venueId") int venueId) {
        return venueService.getVenueWithSeatMap(venueId);
    }

    /**
     * 审批场馆详情视图
     *
     * @param venueId 场馆编号
     * @return 场馆详情视图
     */
    @GetMapping(path = "/audit/venue/{venueId}")
    public String auditVenueView(@PathVariable("venueId") int venueId, Model model) {
        model.addAttribute("venueId", venueId);
        return "/manager/audit-venue";
    }

    /**
     * 经理登录
     *
     * @param id       经理id
     * @param password 经理密码
     * @return 经理登录成功则跳转到审批界面，若未成功则报错
     */
    @PostMapping(path = "/login")
    public String login(@RequestParam("manager-id") int id, @RequestParam("manager-password") String password, Model model) {
        if (managerService.login(id, password)) {
            model.addAttribute("manager-id", id);
            return "redirect:/manager/audit";
        } else {
            model.addAttribute("errorMsg", "账户或密码错误");
            return "/manager/error";
        }
    }

    /**
     * 经理审批
     *
     * @return 经理审批界面
     */
    @RequestMapping(path = "/audit")
    public String auditingView() {
        return "/manager/audit";
    }

    /**
     * 审批界面显示所有场馆计划
     *
     * @return 场馆计划列表
     */
    @FastJsonView(include = {@FastJsonFilter(clazz = Venue.class, props = {"id", "name", "city"})})
    @GetMapping(path = "/audit/venues")
    public @ResponseBody
    List<Venue> auditVenuesBrief() {
        return venueService.getAuditingVenues();
    }
}
