package edu.nju.service.impl;

import edu.nju.dao.VenueDao;
import edu.nju.dto.VenueBasicInfoDTO;
import edu.nju.dto.VenueSeatInfoDTO;
import edu.nju.model.Venue;
import edu.nju.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shenmiu
 * @date 2018/03/15
 *
 * 场馆业务逻辑的实现类
 */
@Service("venueService")
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueDao venueDao;

    @Override
    public Venue getVenue(int venueId) {
        return venueDao.getVenue(venueId);
    }

    @Override
    public boolean register(Venue venue) {
        return venueDao.addVenue(venue);
    }

    @Override
    public boolean login(int venueId, String venuePassword) {
        String savedPassword = venueDao.getPassword(venueId);
        return savedPassword.equals(venuePassword);
    }

    @Override
    public boolean updateBasicInfo(VenueBasicInfoDTO venueBasicInfo) {
        return venueDao.updateBasicInfo(venueBasicInfo);
    }

    @Override
    public boolean updateSeatMap(VenueSeatInfoDTO venueSeatInfo) {
        return venueDao.updateSeatMap(venueSeatInfo);
    }
}
