package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.dto.SeatCheckInDTO;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.service.OrderService;
import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author Shenmiu
 * @date 2018/03/05
 */
@Controller("venueController")
@SessionAttributes(value = {"venueId"}, types = {Integer.class})
@RequestMapping("/venue")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/checkIn")
    public @ResponseBody
    boolean seatCheckIn(@RequestBody SeatCheckInDTO seatCheckInDTO) {
        return venueService.seatCheckIn(seatCheckInDTO);
    }

    /**
     * 场馆计划简介请求检票登记
     *
     * @param venuePlanId 场馆计划id
     * @return 检票登记界面
     */
    @GetMapping(value = "/checkIn/{venuePlanId}")
    public String checkInView(@PathVariable("venuePlanId") int venuePlanId, Model model) {
        model.addAttribute("planDetail", JSON.toJSONString(venueService.getVenuePlanDetail(venuePlanId)));
        return "/venue/checkIn";
    }

    /**
     * 现场购票
     *
     * @param takeOrderDTO 订单数据传输对象
     * @return 购票是否成功
     */
    @PostMapping(value = "/buyOnSite", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    boolean buyOnSite(@RequestBody TakeOrderDTO takeOrderDTO) {
        return orderService.takeOrderOnSite(takeOrderDTO);
    }

    /**
     * 现场购票界面
     *
     * @param venuePlanId 现场购票
     * @return 现场购票界面
     */
    @GetMapping(value = "/buy/{venuePlanId}")
    public String buyView(@PathVariable("venuePlanId") int venuePlanId, Model model) {
        model.addAttribute("planDetail", JSON.toJSONString(venueService.getVenuePlanDetail(venuePlanId)));
        return "/venue/buy-on-site";
    }


    /**
     * 登出，返回主页
     */
    @GetMapping(value = "/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/index";
    }

    /**
     * 场馆注册视图
     */
    @GetMapping(value = "/registerView")
    public String registerView() {
        return "/venue/register";
    }

    /**
     * 场馆信息视图
     *
     * @return 场馆信息视图
     */
    @GetMapping(value = "/infoView")
    public String infoView(@SessionAttribute("venueId") int venueId, Model model) {
        model.addAttribute("venueInfo", JSON.toJSONString(venueService.getVenueWithSeatMap(venueId)));
        return "/venue/info";
    }

    /**
     * 场馆查看计划视图
     *
     * @return 场馆所有计划列表
     */
    @GetMapping(value = "/planView")
    public String planView(@SessionAttribute("venueId") int venueId, Model model) {
        model.addAttribute("venuePlans", venueService.getAllBriefVenuePlan(venueId));
        return "venue/plan-brief";
    }

    /**
     * 计划详情界面
     *
     * @return 场馆计划详情
     */
    @GetMapping(value = "/planView/{venuePlanId}")
    public String planDetailView(@PathVariable("venuePlanId") int venuePlanId, Model model) {
        model.addAttribute("planDetail", JSON.toJSONString(venueService.getVenuePlanDetail(venuePlanId)));
        return "/venue/plan-detail";
    }

    /**
     * 场馆计划发布视图
     *
     * @return 场馆计划发布视图
     */
    @GetMapping(value = "/release-plan")
    public String releasePlanView(@SessionAttribute("venueId") int venueId, Model model) {
        model.addAttribute("venueInfo", JSON.toJSONString(venueService.getVenueWithSeatMap(venueId)));
        return "/venue/release-plan";
    }

    /**
     * 添加一个场馆计划
     *
     * @param venueId   场馆编号
     * @param venuePlan 场馆计划
     * @return 添加是否成功
     */
    @PostMapping(value = "/addPlan", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    boolean addVenuePlan(@SessionAttribute("venueId") int venueId, @RequestBody VenuePlan venuePlan) {
        return venueService.addVenuePlan(venueId, venuePlan);
    }

    /**
     * 场馆注册
     *
     * @param venue 场馆信息
     * @return 场馆注册编号
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    int register(@RequestBody Venue venue) {
        return venueService.register(venue);
    }

    /**
     * 场馆登录
     *
     * @param venueId       场馆编号
     * @param venuePassword 场馆密码
     * @return 重定向至信息界面或者错误界面
     */
    @PostMapping(value = "/login")
    public String login(@RequestParam("venue-id") int venueId, @RequestParam("venue-password") String venuePassword, Model model) {
        if (venueService.login(venueId, venuePassword)) {
            //在Session中加入场馆编号
            model.addAttribute("venueId", venueId);
            return "redirect:/venue/infoView";
        }
        //todo 错误界面
        return "";
    }

    /**
     * 更新场馆的基本信息
     *
     * @param venueBasicInfoDTO 场馆基本信息
     * @return 是否更新成功
     */
    @PostMapping(value = "/updateBasicInfo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    boolean updateBasicInfo(@RequestBody VenueBasicInfoDTO venueBasicInfoDTO) {
        return venueService.updateBasicInfo(venueBasicInfoDTO);
    }

    /**
     * 更新场馆的座位信息
     *
     * @param venueSeatInfoDTO 场馆座位信息
     * @return 是否更新成功
     */
    @PostMapping(value = "/updateSeatMap", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    boolean updateSeatMap(@RequestBody VenueSeatInfoDTO venueSeatInfoDTO) {
        return venueService.updateSeatMap(venueSeatInfoDTO);
    }

}
