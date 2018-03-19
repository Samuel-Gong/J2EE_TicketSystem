package edu.nju.service.impl;

import edu.nju.dao.VenueDao;
import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.model.VenuePlan;
import edu.nju.model.VenueSeat;
import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Shenmiu
 * @date 2018/03/15
 * <p>
 * 场馆业务逻辑的实现类
 */
@Service("venueService")
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueDao venueDao;

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Venue getVenue(int venueId) {
        return venueDao.getVenue(venueId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean register(Venue venue) {
        //venue和venueSeat建立关系
        for (VenueSeat venueSeat : venue.getSeatMap()) {
            venueSeat.setVenue(venue);
        }
        return venueDao.addVenue(venue);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public boolean login(int venueId, String venuePassword) {
        return venueDao.getPassword(venueId).equals(venuePassword);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateBasicInfo(VenueBasicInfoDTO venueBasicInfo) {
        return venueDao.updateBasicInfo(venueBasicInfo);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updateSeatMap(VenueSeatInfoDTO venueSeatInfo) {
        //todo 测试
        Venue venue = getVenue(venueSeatInfo.getVenueId());
        assert venue != null;
        //venueSeat和venue建立联系
        for (VenueSeat venueSeat : venueSeatInfo.getSeatMap()) {
            venueSeat.setVenue(venue);
        }
        //场馆座位的行列数没有变化
        if (venue.getRowNum() == venueSeatInfo.getRowNum() && venue.getColumnNum() == venueSeatInfo.getColumnNum()) {
            //只更新座位上是否有座位
            venueDao.updateSeatMap(venueSeatInfo);
        } else {
            //先删除座位
            venueDao.deleteSeatMap(venue.getId());
            //更新venue的行和列
            venue.setRowNum(venueSeatInfo.getRowNum());
            venue.setColumnNum(venueSeatInfo.getColumnNum());
            venueDao.updateVenue(venue);
            //再添加座位
            venueDao.addSeatMap(venueSeatInfo.getSeatMap());
        }
        //行列数有变化
        return venueDao.updateSeatMap(venueSeatInfo);
    }

    @Override
    public boolean addVenuePlan(int venueId, VenuePlan venuePlan) {
        //todo

        return false;
    }
}
