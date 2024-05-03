package com.pingan.cn.cityopen;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.nongzuowu.PinZhong;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("PoiService")
public class CityOpenPoiService {
    @Autowired
    private CityOpenPoiDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public CityOpenPoi saveAction(CityOpenPoi action){
        PinZhong save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(CityOpenPoi action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<CityOpenPoi> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            CityOpenPoi oldAction = actionDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                actionDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<CityOpenPoi> findAll(){
        return actionDao.findAll();
//        return actionDao.findAllLimit10000();
    }

    public CityOpenPoi findById(Integer id){
        return actionDao.findById(id).get();
    }

    public boolean deleteById(Integer id){
        try {
            actionDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBatch(Integer[] ids){
        ArrayList<CityOpenPoi> arrayList = new ArrayList<>();
        for (Integer id:ids) {
            CityOpenPoi entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            actionDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

//    public List<CityOpenPoi> findByDate(String date){
//        return actionDao.findByDate(date);
//    }
//
//    public List<CityOpenPoi> findByBetweenDate(String start , String end){
//        return actionDao.findByBetweenDate(start,end);
//    }
//
//    public List<CityOpenPoi> findDistinctLngLatByBetweenDate(String start , String end){
//        return actionDao.findDistinctLngLatByBetweenDate(start,end);
//    }
//
//    public List<Integer> countOnlineByHour(){
//        return actionDao.countOnlineByHour();
//    }

}
