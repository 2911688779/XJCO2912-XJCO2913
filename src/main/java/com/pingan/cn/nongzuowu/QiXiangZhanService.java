package com.pingan.cn.nongzuowu;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("qiXiangZhanService")
public class QiXiangZhanService {
    @Autowired
    private QiXiangZhanDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public QiXiangZhan saveAction(QiXiangZhan action){
        QiXiangZhan save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(QiXiangZhan action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<QiXiangZhan> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            QiXiangZhan oldAction = actionDao.findById(action.getId()).get();
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

    public List<QiXiangZhan> findAll(){
        return actionDao.findAll();
    }

    public QiXiangZhan findById(Integer id){
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
        ArrayList<QiXiangZhan> arrayList = new ArrayList<>();
        for (Integer id:ids) {
            QiXiangZhan entity = findById(id);
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
}
