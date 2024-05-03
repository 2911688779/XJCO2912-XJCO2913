package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionDao extends JpaRepository<Action,String> {

}
