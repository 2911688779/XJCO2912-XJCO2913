package com.pingan.cn.route;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.common.utils.SpringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("momentService")
public class MomentService {
    @Autowired
    private MomentDao momentDao;
    @Autowired
    private CommentsDao commentsDao;

    //注册
    public ResponseUtil save(Moment bean){
        try {
            Moment result = momentDao.saveAndFlush(bean);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    //查询所有
    public ResponseUtil findAll(){
        try {
            List<Moment> beans = momentDao.findAll();
            return ResponseUtil.success(beans);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findByUserId(String userId){
        try {
//            List<Moment> beans = momentDao.findAllByUserId(userId);
            List<Moment> beans = momentDao.findAllByUserIdOrderByMomentTimeDesc(userId);
            return ResponseUtil.success(beans);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findByUserIds(List<String> userIds) {
        try {
//            List<Moment> beans = momentDao.findAllByUserId(userId);
            List<Moment> beans = momentDao.findByUserIds(userIds);
            return ResponseUtil.success(beans);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil deleteById(String id){
        try {
            momentDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

//    批量删除
    public boolean deleteBatch(String[] ids){
        ArrayList<Moment> arrayList = new ArrayList<>();
        for (String id:ids) {
            Moment entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            momentDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Moment findById(String id){
        return momentDao.findById(id).get();
    }

    public boolean update(Moment action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<Moment> byId = momentDao.findById(action.getId());
        if (byId.isPresent()){
            Moment oldAction = momentDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                momentDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
