package com.pingan.cn.route;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.common.utils.SpringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("monmentService")
public class MonmentService {
    @Autowired
    private MonmentDao monmentDao;

    //注册
    public ResponseUtil save(Monment bean){
        try {
            Monment result = monmentDao.saveAndFlush(bean);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    //查询所有
    public ResponseUtil findAll(){
        try {
            List<Monment> beans = monmentDao.findAll();
            return ResponseUtil.success(beans);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil deleteById(String id){
        try {
            monmentDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

//    批量删除
    public boolean deleteBatch(String[] ids){
        ArrayList<Monment> arrayList = new ArrayList<>();
        for (String id:ids) {
            Monment entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            monmentDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Monment findById(String id){
        return monmentDao.findById(id).get();
    }

    public boolean update(Monment action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<Monment> byId = monmentDao.findById(action.getId());
        if (byId.isPresent()){
            Monment oldAction = monmentDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                monmentDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
