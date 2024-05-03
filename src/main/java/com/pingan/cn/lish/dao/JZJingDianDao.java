package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.JZJingDian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JZJingDianDao extends JpaRepository<JZJingDian,String> {

    public JZJingDian findByName(String name);
}
