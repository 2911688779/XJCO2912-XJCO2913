package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.DiquNames;
import com.pingan.cn.lish.entity.Moyu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoyuDao extends JpaRepository<Moyu,String> {

    List<Moyu> findByDiqu(DiquNames diqu);
}
