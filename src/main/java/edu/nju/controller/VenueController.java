package edu.nju.controller;

import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenuePlanBriefDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String infoView() {
        return "/venue/info";
    }

    /**
     * 场馆查看计划视图
     *
     * @return 场馆所有计划列表
     */
    @GetMapping(value = "/planView")
    public String planView() {
        return "venue/plan-brief";
    }

///todo    计划详情界面
//    @GetMapping(value = "/plan-detail/{venuePlanId}")
//    public String planDetailView() {
//
//    }

    /**
     * 场馆计划发布视图
     *
     * @return 场馆计划发布视图
     */
    @GetMapping(value = "/release-plan")
    public String releasePlanView() {
        return "/venue/release-plan";
    }

    /**
     * 获取场馆对应的所有场馆计划
     *
     * @param venueId 场馆编号
     * @return 场馆计划列表
     */
    @GetMapping(value = "/getAllPlan")
    public @ResponseBody
    List<VenuePlanBriefDTO> getVenuePlan(@SessionAttribute("venueId") int venueId) {
        return venueService.getAllBriefVenuePlan(venueId);
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
     * 获取场馆信息
     *
     * @param venueId 场馆编号
     * @return 场馆信息json格式
     */
    @GetMapping(value = "/info")
    public @ResponseBody
    Venue infoView(@SessionAttribute("venueId") int venueId) {
        //只需要获取带座位信息的场馆
        return venueService.getVenueWithSeatMap(venueId);
    }

    /**
     * 场馆注册
     *
     * @param venue 场馆信息
     * @return 注册是否成功
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    boolean register(@RequestBody Venue venue) {
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
