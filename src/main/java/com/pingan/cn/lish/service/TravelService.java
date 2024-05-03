package com.pingan.cn.lish.service;

import com.pingan.cn.lish.dao.TravelDao;
import com.pingan.cn.lish.entity.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("travelService")
public class TravelService {
    @Autowired
    private TravelDao travelDao;

    public Travel saveTravel(Travel travel){
        try{
            return travelDao.save(travel);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Travel> findTravelOrderByDate(String p_id){
        return travelDao.findByPersonIdOrderByDate(p_id);
    }
}
