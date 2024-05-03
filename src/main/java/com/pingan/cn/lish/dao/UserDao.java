package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    User findByUsernameAndPassword(String username,String password);
}
