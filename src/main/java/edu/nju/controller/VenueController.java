package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.dto.LevelAndDiscountDTO;
import edu.nju.dto.SeatCheckInDTO;
import edu.nju.dto.TakeOrderDTO;
import edu.nju.dto.VenueFinanceDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.service.MemberService;
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

    @Autowired
    private MemberService memberService;

    @GetMapping(path = "/statistics/details")
    public @ResponseBody
    VenueFinanceDTO getStatistics(@SessionAttribute("venueId") int venueId) {
        return venueService.getFinance(venueId);
    }

    /**
     * 查看场馆统计
     *
     * @return 场馆统计视图
     */
    @GetMapping(path = "/statistics")
    public String statisticsView() {
        return "/venue/statistics";
    }

    @RequestMapping(path = "/discount")
    public @ResponseBody
    LevelAndDiscountDTO getMemberDiscount(@RequestParam("mail") String mail) {
        return memberService.getLevelAndDiscount(mail);
//        return new LevelAndDiscountDTO();
    }

    /**
     * 检查场馆信息是否可以修改
     *
     * @param venueId 场馆编号
     * @return 场馆信息是否可以修改
     */
    @GetMapping(value = "/modify/check")
    public @ResponseBody
    boolean modifyCheck(@SessionAttribute("venueId") int venueId) {
        return venueService.modifyCheck(venueId);
    }

    /**
     * 检票
     *
     * @param seatCheckInDTO 检票的数据传输对象
     * @return 检票是否成功
     */
    @PostMapping(value = "/checkIn", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
        return orderService.addOrder(takeOrderDTO) > 0;
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
     * 场馆未完成计划视图
     *
     * @param venueId 场馆id
     * @return 场馆未完成计划列表
     */
    @GetMapping(value = "/plan/coming")
    public String comingPlan(@SessionAttribute("venueId") int venueId, Model model) {
        model.addAttribute("venuePlans", venueService.getComingVenuePlans(venueId));
        return "venue/plan/coming";
    }

    /**
     * 场馆未结算计划视图
     *
     * @param venueId 场馆id
     * @param model   场馆未结算计划列表
     * @return 场馆未结算计划列表
     */
    @GetMapping(value = "/plan/unsettle")
    public String unsettlePlan(@SessionAttribute("venueId") int venueId, Model model) {
        model.addAttribute("venuePlans", venueService.getUnsettlePlans(venueId));
        return "venue/plan/unsettle";
    }

    /**
     * 场馆已结算计划视图
     *
     * @param venueId 场馆id
     * @param model   场馆已结算计划列表
     * @return 场馆已结算计划列表
     */
    @GetMapping(value = "/plan/settle")
    public String settlePlan(@SessionAttribute("venueId") int venueId, Model model) {
        model.addAttribute("venuePlans", venueService.getSettlePlans(venueId));
        return "venue/plan/settle";
    }

    /**
     * 计划详情界面
     *
     * @return 场馆计划详情
     */
    @GetMapping(value = "/plan/{venuePlanId}")
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
     * @param venue 场馆登陆信息
     * @return 场馆登陆是否成功
     */
    @PostMapping(value = "/login")
    public @ResponseBody
    boolean login(@RequestBody Venue venue, Model model) {
        if (venueService.login(venue)) {
            //在Session中加入场馆编号
            model.addAttribute("venueId", venue.getId());
            return true;
        }
        return false;
    }

    /**
     * 更新场馆信息
     *
     * @param venue 场馆信息
     * @return 是否更新成功
     */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    boolean updateVenueInfo(@RequestBody Venue venue) {
        return venueService.updateVenue(venue);
    }

}
