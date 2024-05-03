package com.pingan.cn.nongzuowu;

import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.common.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("qixiangYearService")
public class QixiangYearService {
    @Autowired
    private QixiangYearDao actionDao;
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public QixiangYear saveAction(QixiangYear action){
        QixiangYear save = null;
        try {
            return actionDao.save(action);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(QixiangYear action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<QixiangYear> byId = actionDao.findById(action.getId());
        if (byId.isPresent()){
            QixiangYear oldAction = actionDao.findById(action.getId()).get();
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

    public List<QixiangYear> findAll(){
        return actionDao.findAll();
    }

    public QixiangYear findById(Integer id){
        return actionDao.findById(id).get();
    }

    /*public QixiangYear findCurrent(){
        List<QixiangYear> actions = findAll();
        //降序
        List<QixiangYear> collect = actions.stream().sorted(new Comparator<Qixiang>() {
            @Override
            public int compare(QixiangYear o1, QixiangYear o2) {
                try {
                    Date d1 = sdf.parse(o1.getDate());
                    Date d2 = sdf.parse(o2.getDate());
                    return d2.compareTo(d1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        }).collect(Collectors.toList());

        System.out.println(Arrays.toString(actions.toArray()));
        return collect.get(0);
    }*/

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
        ArrayList<QixiangYear> arrayList = new ArrayList<>();
        for (Integer id:ids) {
            QixiangYear entity = findById(id);
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

    public List<QixiangYear> findByYear(int year){
        return  actionDao.findByYear(year);
    }
}
