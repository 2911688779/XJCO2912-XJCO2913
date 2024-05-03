package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.Fangyuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FangyuanDao extends JpaRepository<Fangyuan,Integer> {
    List<Fangyuan> findByUsername(String username);
}
