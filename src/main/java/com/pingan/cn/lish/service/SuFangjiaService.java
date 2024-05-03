package com.pingan.cn.lish.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.lish.dao.SuFangjiaDao;
import com.pingan.cn.lish.entity.SuFangjia;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("suFangjiaService")
public class SuFangjiaService {

    @Autowired
    private SuFangjiaDao suFangjiaDao;

    public ResponseUtil save(SuFangjia poi){
        try {
            SuFangjia result = suFangjiaDao.saveAndFlush(poi);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public boolean update(SuFangjia common){
        if(common == null || common.getId() == null){
            return  false;
        }
        Optional<SuFangjia> byId = suFangjiaDao.findById(common.getId());
        if (byId.isPresent()){
            SuFangjia oldCommon = suFangjiaDao.findById(common.getId()).get();
            BeanUtils.copyProperties(common,oldCommon, SpringUtil.getNullPropertyNames(common));
            try {
                suFangjiaDao.saveAndFlush(oldCommon);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public SuFangjia findById(int id){
        return suFangjiaDao.findById(id).get();
    }

    public boolean deleteById(int id){
        try {
            suFangjiaDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ResponseUtil findAll(){
        try {
            List<SuFangjia> result = suFangjiaDao.findAll();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findAllDesc(){
        try {
            List<SuFangjia> result = suFangjiaDao.findAllOrderByJjDesc();
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public  List<SuFangjia> findParams(String xqmc, String xzq, String jd,String wylx, int jznd,  double jj_low, double jj_high){
//        if("ALL".equals(xqmc.toUpperCase())) xqmc = "";
//        if("ALL".equals(xzq.toUpperCase())) xzq = "";
//        if("ALL".equals(jd.toUpperCase())) jd = "";
//        if("ALL".equals(wylx.toUpperCase())) wylx = "";
        try {
            return  suFangjiaDao.findParams(xqmc, xzq, jd, wylx, jznd, jj_low, jj_high);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<SuFangjia> findByXqmcLike(String xqmc){
        try {
            return  suFangjiaDao.findByXqmcLike(xqmc);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
