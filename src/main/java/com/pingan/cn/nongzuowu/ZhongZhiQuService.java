package com.pingan.cn.nongzuowu;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("zhongZhiQuService")
public class ZhongZhiQuService {
    @Autowired
    private ZhongZhiQuDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public ZhongZhiQu saveAction(ZhongZhiQu action){
        ZhongZhiQu save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(ZhongZhiQu action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<ZhongZhiQu> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            ZhongZhiQu oldAction = actionDao.findById(action.getId()).get();
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

    public List<ZhongZhiQu> findAll(){
        return actionDao.findAll();
    }

    public ZhongZhiQu findById(Integer id){
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
        ArrayList<ZhongZhiQu> arrayList = new ArrayList<>();
        for (Integer id:ids) {
            ZhongZhiQu entity = findById(id);
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

    public List<String> findDistinctRegion(){
        return actionDao.findDistinctRegion();
    }
}
