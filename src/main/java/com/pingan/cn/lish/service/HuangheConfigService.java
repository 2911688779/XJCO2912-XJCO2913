package com.pingan.cn.lish.service;

import com.pingan.cn.lish.dao.HuangheConfigDao;
import com.pingan.cn.lish.entity.HuangheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("huangheConfigService")
public class HuangheConfigService {

    @Autowired
    private HuangheConfigDao huangheConfigDao;

    public List<HuangheConfig> findAll(){
        return huangheConfigDao.findAll();
    }
}
