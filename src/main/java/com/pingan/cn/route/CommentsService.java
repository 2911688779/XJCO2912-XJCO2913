package com.pingan.cn.route;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.common.utils.SpringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("commentsService")
public class CommentsService {
    @Autowired
    private CommentsDao commentsDao;

    //注册
    public ResponseUtil save(Comments bean){
        try {
            Comments result = commentsDao.saveAndFlush(bean);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    //查询所有
    public ResponseUtil findAll(){
        try {
            List<Comments> beans = commentsDao.findAll();
            return ResponseUtil.success(beans);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil deleteById(String id){
        try {
            commentsDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

//    批量删除
    public boolean deleteBatch(String[] ids){
        ArrayList<Comments> arrayList = new ArrayList<>();
        for (String id:ids) {
            Comments entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            commentsDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Comments findById(String id){
        return commentsDao.findById(id).get();
    }

    public boolean update(Comments action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<Comments> byId = commentsDao.findById(action.getId());
        if (byId.isPresent()){
            Comments oldAction = commentsDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                commentsDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
