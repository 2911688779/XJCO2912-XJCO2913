package com.pingan.cn.lmsg;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sgfileService")
public class SgfileService {

    @Value("${dataserver.folder}")
    private String FILELoction;

    @Autowired
    private SgfileDao actionDao;


    public Sgfile saveAction(Sgfile action){
        try {
            actionDao.save(action);
            return action;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Sgfile> findAll(){
        return actionDao.findAll();
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

    public Sgfile findById(String id){
        return actionDao.findById(id).get();
    }

    public boolean deleteBatch(String[] ids){
        ArrayList<Sgfile> arrayList = new ArrayList<>();
        for (String id:ids) {
            Sgfile entity = findById(id);
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
