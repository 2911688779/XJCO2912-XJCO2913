package com.pingan.cn.lish.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.dao.FangyuanDao;
import com.pingan.cn.lish.entity.Fangyuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fangyuanService")
public class FangyuanService {
    @Autowired
    private FangyuanDao fangyuanDao;

    public ResponseUtil save(Fangyuan fangyuan){
        try {
            Fangyuan result = fangyuanDao.saveAndFlush(fangyuan);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findAll(){
        try {
            List<Fangyuan> result = fangyuanDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findByUsername(String username){
        try {
            List<Fangyuan> result = fangyuanDao.findByUsername(username);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil deleteById(Integer id){
        try {
            fangyuanDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }
}
