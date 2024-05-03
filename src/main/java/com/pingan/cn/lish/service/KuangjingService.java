package com.pingan.cn.lish.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.dao.KuangjingDao;
import com.pingan.cn.lish.entity.Kuangjing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("kuangjingService")
public class KuangjingService {
    @Autowired
    private KuangjingDao kuangjingDao;

    public ResponseUtil save(Kuangjing kuangjing){
        try {
            Kuangjing result = kuangjingDao.saveAndFlush(kuangjing);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findAll(){
        try {
            List<Kuangjing> result = kuangjingDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public List<Kuangjing> findAllKuangjings(){
        return kuangjingDao.findAll();
    }

    public ResponseUtil deleteById(String id){
        try {
            kuangjingDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }
}
