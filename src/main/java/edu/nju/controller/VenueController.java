package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String registerView(){
        return "/venue/register";
    }

    /**
     * 场馆信息视图
     * @param venueId 场馆编号
     * @param model model
     * @return 场馆信息视图
     */
    @GetMapping(value = "/info")
    public String infoView(@SessionAttribute("venueId") int venueId, Model model){
        Venue venue = venueService.getVenue(venueId);
        model.addAttribute("venue", JSON.toJSONString(venue));
        return "/venue/info";
    }

    /**
     * 场馆注册
     * @param venue 场馆信息
     * @return 注册是否成功
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody boolean register(@RequestBody Venue venue){
        return venueService.register(venue);
    }

    /**
     * 场馆登录
     * @param venueId   场馆编号
     * @param venuePassword  场馆密码
     * @return 重定向至信息界面或者错误界面
     */
    @PostMapping(value = "/login")
    public String login(@RequestParam("venue-id") int venueId, @RequestParam("venue-password") String venuePassword, Model model){
        if(venueService.login(venueId, venuePassword)){
            //在Session中加入场馆编号
            model.addAttribute("venueId", venueId);
            return "redirect:/venue/info";
        }
        //todo 错误界面
        return "";
    }

    /**
     * 更新场馆的基本信息
     * @param venueBasicInfoDTO 场馆基本信息
     * @return 是否更新成功
     */
    @PostMapping(value = "/updateBasicInfo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody boolean updateBasicInfo(@RequestBody VenueBasicInfoDTO venueBasicInfoDTO){
        return venueService.updateBasicInfo(venueBasicInfoDTO);
    }

    /**
     * 更新场馆的座位信息
     * @param venueSeatInfoDTO 场馆座位信息
     * @return 是否更新成功
     */
    @PostMapping(value = "/updateSeatMap", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody boolean updateSeatMap(@RequestBody VenueSeatInfoDTO venueSeatInfoDTO){
        return venueService.updateSeatMap(venueSeatInfoDTO);
    }

}
