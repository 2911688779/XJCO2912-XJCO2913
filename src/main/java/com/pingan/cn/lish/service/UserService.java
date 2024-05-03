package com.pingan.cn.lish.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.common.utils.SpringUtil;
import com.pingan.cn.lish.dao.UserDao;
import com.pingan.cn.lish.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    //注册
    public ResponseUtil register(User user){
        try {
            User result = userDao.saveAndFlush(user);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    //登录查找
    public ResponseUtil findByUsernameAndPassword(String username, String password){
        try {
            User result = userDao.findByUsernameAndPassword(username,password);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    //查询所有
    public ResponseUtil findAll(){
        try {
            List<User> users = userDao.findAll();
            return ResponseUtil.success(users);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil deleteById(String id){
        try {
            userDao.deleteById(id);
            return ResponseUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

//    批量删除
    public boolean deleteBatch(String[] ids){
        ArrayList<User> arrayList = new ArrayList<>();
        for (String id:ids) {
            User entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            userDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public User findById(String id){
        return userDao.findById(id).get();
    }

    public boolean update(User action){
        if(action == null || action.getId() == null){
            return  false;
        }
        Optional<User> byId = userDao.findById(action.getId());
        if (byId.isPresent()){
            User oldAction = userDao.findById(action.getId()).get();
            BeanUtils.copyProperties(action,oldAction, SpringUtil.getNullPropertyNames(action));
            try {
                userDao.saveAndFlush(oldAction);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
