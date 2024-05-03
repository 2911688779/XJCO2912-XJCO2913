package com.pingan.cn.lish.service;


import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.dao.JZJingDianDao;
import com.pingan.cn.lish.entity.JZJingDian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("JZJingDianService")
public class JZJingDianService {
    @Autowired
    private JZJingDianDao jzJingDianDao;

    public ResponseUtil save(JZJingDian jzJingDian){
        try {
            JZJingDian result = jzJingDianDao.saveAndFlush(jzJingDian);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findById(String id){
        Optional<JZJingDian> byId = jzJingDianDao.findById(id);
        if(!byId.isPresent()){
            return ResponseUtil.success(byId.get());
        }else{
            return ResponseUtil.error();
        }
    }

    public ResponseUtil findByName(String name){
        try{
            return ResponseUtil.success(jzJingDianDao.findByName(name));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseUtil.error(e);
        }
    }
}
