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

    @GetMapping(path = "/test")
    public @ResponseBody
    boolean test() {
        return true;
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
