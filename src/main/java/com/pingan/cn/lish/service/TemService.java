package com.pingan.cn.lish.service;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.lish.dao.TemDao;
import com.pingan.cn.lish.entity.Tem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("temService")
public class TemService {
    @Autowired
    private TemDao temDao;

    public Tem saveAction(Tem person){
        Tem save = null;
        try {
            return temDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Tem action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<Tem> byId = temDao.findById(action.getId());
        if (byId.isPresent()){
            Tem oldAction = temDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                temDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public List<Tem> findAll(){
        return temDao.findAll();
    }

    public Tem findById(Long id){
        return temDao.findById(id).get();
    }

    public boolean deleteById(Long id){
        try {
            temDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBatch(Long[] ids){
        ArrayList<Tem> arrayList = new ArrayList<>();
        for (Long id:ids) {
            Tem entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            temDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Tem> findByDate(String year,String month,String day){
//        return temDao.findByYearLikeAndMonthLikeAndDayLike(year,month,day);
        return temDao.findByYearAndMonthAndDay(year,month,day);
    }
}
