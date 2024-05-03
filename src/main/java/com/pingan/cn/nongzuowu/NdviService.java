package com.pingan.cn.nongzuowu;

import com.pingan.cn.common.utils.SpringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("ndviService")
public class NdviService {
    @Autowired
    private NdviDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public Ndvi saveAction(Ndvi action){
        Ndvi save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Ndvi action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<Ndvi> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Ndvi oldAction = actionDao.findById(action.getId()).get();
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

    public List<Ndvi> findAll(){
        return actionDao.findAll();
    }

    public Ndvi findById(Integer id){
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
        ArrayList<Ndvi> arrayList = new ArrayList<>();
        for (Integer id:ids) {
            Ndvi entity = findById(id);
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

    public List<Ndvi> findByDay(String day){
        return actionDao.findByDateLike("%"+day+"%");
    }

    public List<Ndvi> findByBetweenDate(String start,String end){
        return actionDao.findByBetweenDate(start,end);
    }
}
