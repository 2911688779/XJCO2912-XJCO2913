package com.pingan.cn.route;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.common.utils.SpringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("activityService")
public class ActivityService {
    @Autowired
    private ActivityDao activityDao;

    //注册
    public ResponseUtil save(Activity bean){
        try {
            Activity result = activityDao.saveAndFlush(bean);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    //查询所有
    public ResponseUtil findAll(){
        try {
            List<Activity> beans = activityDao.findAll();
            return ResponseUtil.success(beans);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil deleteById(String id){
        try {
            activityDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

//    批量删除
    public boolean deleteBatch(String[] ids){
        ArrayList<Activity> arrayList = new ArrayList<>();
        for (String id:ids) {
            Activity entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            activityDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Activity findById(String id){
        return activityDao.findById(id).get();
    }

    public boolean update(Activity action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<Activity> byId = activityDao.findById(action.getId());
        if (byId.isPresent()){
            Activity oldAction = activityDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                activityDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
