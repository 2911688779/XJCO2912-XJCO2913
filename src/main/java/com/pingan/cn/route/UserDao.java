package com.pingan.cn.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User,String> {

    User findByUsernameAndPassword(String username,String password);
}
