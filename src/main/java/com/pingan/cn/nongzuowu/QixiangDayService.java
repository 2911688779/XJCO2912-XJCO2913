package com.pingan.cn.nongzuowu;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("qixiangDayService")
public class QixiangDayService {
    @Autowired
    private QixiangDayDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public QixiangDay saveAction(QixiangDay action){
        QixiangDay save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(QixiangDay action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<QixiangDay> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            QixiangDay oldAction = actionDao.findById(action.getId()).get();
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

    public List<QixiangDay> findAll(){
        return actionDao.findAll();
    }

    public QixiangDay findById(Integer id){
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
        ArrayList<QixiangDay> arrayList = new ArrayList<>();
        for (Integer id:ids) {
            QixiangDay entity = findById(id);
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

    public List<QixiangDay> findByDay(String day){
        return actionDao.findByDateLike("%"+day+"%");
    }

    public List<QixiangDay> findByBetweenDate(String start,String end){
        return actionDao.findByBetweenDate(start,end);
    }
}