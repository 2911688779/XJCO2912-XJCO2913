package com.pingan.cn.fangxun;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("qiangxianService")
public class QiangXianService {
    @Autowired
    private QiangXianDao actionDao;

    public QiangXian saveAction(QiangXian action){
        QiangXian save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(QiangXian action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<QiangXian> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            QiangXian oldAction = actionDao.findById(action.getId()).get();
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

    public List<QiangXian> findAll(){
        return actionDao.findAll();
    }

    public QiangXian findById(String id){
        return actionDao.findById(id).get();
    }

    public boolean deleteById(String id){
        try {
            actionDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBatch(String[] ids){
        ArrayList<QiangXian> arrayList = new ArrayList<>();
        for (String id:ids) {
            QiangXian entity = findById(id);
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
