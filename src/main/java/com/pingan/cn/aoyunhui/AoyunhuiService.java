package com.pingan.cn.aoyunhui;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import com.pingan.cn.guanqu.Shuiwei;
import com.pingan.cn.guanqu.ShuiweiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("aoyunhuiService")
public class AoyunhuiService {
    @Autowired
    private AoyunhuiDao actionDao;

    @Autowired
    private ShuiweiService shuiweiService;

    public Aoyunhui saveAction(Aoyunhui person){
        Aoyunhui save = null;
        try {
            return actionDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Aoyunhui action){
        if(action == null || StrUtil.isBlank(action.getId())){
            return  false;
        }
        Optional<Aoyunhui> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            Aoyunhui oldAction = actionDao.findById(action.getId()).get();
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

    public List<Aoyunhui> findAll(){
        return actionDao.findAll();
    }

//    public List<AoyunhuiVo> findAllWithShuiwei(){
//        List<Aoyunhui> lists = actionDao.findAll();
//        List<AoyunhuiVo> result = new ArrayList<>();
//        for (int i=0;i<lists.size();i++){
//            String num = lists.get(i).getNum();
//            Shuiwei shui = shuiweiService.findBySTNum(num);
//
//            Aoyunhui tangba = lists.get(i);
//            AoyunhuiVo vo = new AoyunhuiVo();
//            vo.setShuiwei(shui);
//            vo.setArea(tangba.getArea());
//            vo.setGate(tangba.getGate());
//            vo.setId(tangba.getId());
//            vo.setMax_water(tangba.getMax_water());
//            vo.setName(tangba.getName());
//            vo.setNum(tangba.getNum());
//            vo.setPosition(tangba.getPosition());
//
//            result.add(vo);
//        }
//        return result;
//    }


    public Aoyunhui findById(String id){
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
        ArrayList<Aoyunhui> arrayList = new ArrayList<>();
        for (String id:ids) {
            Aoyunhui entity = findById(id);
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
