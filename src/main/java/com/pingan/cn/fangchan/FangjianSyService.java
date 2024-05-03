package com.pingan.cn.fangchan;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fangjianSyService")
public class FangjianSyService {
    @Autowired
    private FangjianSyDao actionDao;

    public FangjianSy saveAction(FangjianSy action){
        FangjianSy save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(FangjianSy action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<FangjianSy> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            FangjianSy oldAction = actionDao.findById(action.getId()).get();
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

    public List<FangjianSy> findAll(){
        return actionDao.findAll();
    }

    public FangjianSy findById(String id){
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
        ArrayList<FangjianSy> arrayList = new ArrayList<>();
        for (String id:ids) {
            FangjianSy entity = findById(id);
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
