package com.pingan.cn.nongzuowu;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("pinZhongService")
public class PinZhongService {
    @Autowired
    private PinZhongDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public PinZhong saveAction(PinZhong action){
        PinZhong save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(PinZhong action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<PinZhong> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            PinZhong oldAction = actionDao.findById(action.getId()).get();
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

    public List<PinZhong> findAll(){
        return actionDao.findAll();
    }

    public PinZhong findById(Integer id){
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
        ArrayList<PinZhong> arrayList = new ArrayList<>();
        for (Integer id:ids) {
            PinZhong entity = findById(id);
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

    public List<PinZhong> findInNumbers(String[] numbers){
        return actionDao.findByNumberIn(numbers);
    }

    public List<String> findDistinctType(){
        return actionDao.findDistinctType();
    }

    public List<PinZhong> findCondition(String year,String region,String type){
        return actionDao.findCondition(year,region,type);
    }

}
